package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import entities.Sale;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Sale> list = new ArrayList<>();
			
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2],
						Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
				line = br.readLine();
			}
			//list.forEach(System.out::println);
			
			Set<String> set = new HashSet<>();
			
			for (Sale vend : list) {
				set.add(vend.getSeller());
			}
			
			System.out.println();
			System.out.println("Total de vendas por vendedor:");
			
			Map<String, Double> map = new HashMap<>();
					
			Double totalPorVendedor;
			
			for (String vendedores : set) {
				totalPorVendedor = list.stream().filter(v -> v.getSeller().equals(vendedores))
						.map(v -> v.getTotal())
						.reduce(0.0, (x, y) -> x + y);
				map.put(vendedores, totalPorVendedor);
			}
			
			for (String vendedor : map.keySet()) {
				System.out.printf(vendedor + " - R$ %.2f%n", map.get(vendedor));
			}
				
		} 
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		sc.close();

	}

}
