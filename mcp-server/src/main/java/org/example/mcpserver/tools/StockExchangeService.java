package org.example.mcpserver.tools;

import jdk.jfr.Description;
import org.springframework.ai.tool.annotation.Tool;

import java.time.LocalDate;
import java.util.List;

public class StockExchangeService {
    private List<Company> companies = List.of(
            new Company("Capgemini","Tech",3.6,10600,"France"),
            new Company("BP","Société pétrolière ",5.6,20000,"Angleterre"),
            new Company("GALP","Société pétrolière ",5.6,20000,"Portugal")
    );
    @Tool(description = "Fournit des informations détaillées sur une entreprise cotée en bourse, y compris son chiffre d'affaires, son nombre d'employés. À utiliser uniquement quand le nom donné est explicitement celui d'une entreprise ou d'une société à analyser.")
    public Company getCompanyByName(String companyName) {
        System.out.println("getCompanyByName called: " + companyName);
        return companies.stream().filter(c->c.name().equals(companyName)).findFirst()
                .orElseThrow(()->new RuntimeException(String.format("Company %s not found", companyName)));
    }
    @Tool(description = "Fournit des informations détaillés des entreprises en France")
    public List<Company> getAllCompanies() {
        System.out.println("getAllCompanies called");
        return companies;
    }
    @Tool
    public Stock getStockByCompany(String companyName) {
        System.out.println("StocksParCompany called :" + companyName);
        return new Stock(companyName, LocalDate.now(), 100+Math.random()*1000);
    }
}

record Company(
        String name,
        String activity,
        @Description("The turnover in Milliard $")
        double turnover,
        int employeesNumber, String country){}
record Stock(String companyName, LocalDate date, double stock){ }