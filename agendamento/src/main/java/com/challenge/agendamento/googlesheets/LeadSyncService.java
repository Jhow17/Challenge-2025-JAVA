package com.challenge.agendamento.googlesheets;



import com.challenge.agendamento.model.Lead;
import com.challenge.agendamento.model.StatusLead;
import com.challenge.agendamento.repository.LeadRepository;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeadSyncService {

    @Autowired
    private Sheets sheetsService;

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private ControleSincronizacaoRepository controleRepository;

    @Value("${google.sheets.spreadsheet-id}")
    private String spreadsheetId;

    private final String RANGE = "Página1!A2:H";

    public List<Lead> sincronizarLeadsIncrementais() {
        List<Lead> todosLeadsSalvos = new ArrayList<>();

        try {
            ControleSincronizacao controle = controleRepository.findById("LEADS_GOOGLE")
                    .orElse(new ControleSincronizacao("LEADS_GOOGLE", 1));

            int proximaLinha = controle.getUltimaLinhaProcessada() + 1;


            String rangeDinamico = "FormResponses1!A" + proximaLinha + ":H";
            System.out.println("Buscando dados no range: " + rangeDinamico);

            ValueRange response = sheetsService.spreadsheets().values()
                    .get(spreadsheetId, rangeDinamico)
                    .execute();

            List<List<Object>> values = response.getValues();

            if (values == null || values.isEmpty()) {
                System.out.println("Nenhum lead novo encontrado a partir da linha " + proximaLinha);
                return todosLeadsSalvos;
            }

            for (List<Object> row : values) {
                try {
                    // Novo Mapeamento considerando a coluna A (índice 0) como Timestamp do Google Forms
                    // Índice 0: Timestamp (ignorado)
                    // Índice 1: Email
                    // Índice 2: Nome
                    // Índice 3: Telefone
                    // Índice 4: Procedimento
                    // Índice 5: Canal
                    
                    Lead lead = new Lead();
                    
                    // Email (Índice 1)
                    lead.setEmail(row.size() > 1 ? row.get(1).toString() : null);
                    
                    // Nome (Índice 2)
                    lead.setNome(row.size() > 2 ? row.get(2).toString() : "Sem Nome");
                    
                    // Telefone (Índice 3)
                    lead.setTelefone(row.size() > 3 ? row.get(3).toString() : null);
                    
                    // Procedimento de Interesse (Índice 4)
                    lead.setProcedimentoInteresse(row.size() > 4 ? row.get(4).toString() : null);
                    
                    // Canal de Entrada (Índice 5)
                    String canal = (row.size() > 5 && !row.get(5).toString().isBlank()) 
                                   ? row.get(5).toString() 
                                   : "Google Forms";
                    lead.setCanalEntrada(canal);
                    
                    todosLeadsSalvos.add(lead);
                } catch (Exception e) {
                    System.out.println("Erro ao processar linha da planilha: " + e.getMessage());
                }
            }

            if (!todosLeadsSalvos.isEmpty()) {
                todosLeadsSalvos = leadRepository.saveAll(todosLeadsSalvos);
                System.out.println(todosLeadsSalvos.size() + " leads salvos com sucesso.");
            }

            int novaUltimaLinha = (proximaLinha - 1) + values.size();

            controle.setUltimaLinhaProcessada(novaUltimaLinha);
            controleRepository.save(controle);

            System.out.println("Sincronização concluída. Marcador atualizado para a linha: " + novaUltimaLinha);

        } catch (Exception exception) {
            System.out.println("Erro na sincronização: " + exception.getMessage());
            throw new RuntimeException("Falha na sincronização com Google Sheets", exception);
        }

        return todosLeadsSalvos;
    }
}
