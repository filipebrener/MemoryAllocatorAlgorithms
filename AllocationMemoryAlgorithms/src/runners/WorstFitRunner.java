package runners;

import memory.allocator.BestFit;
import memory.allocator.QuickFit;
import memory.allocator.WorstFit;
import memory.model.Allocation;

public class WorstFitRunner extends Runner {

    public void run(){
        System.out.println("\n\n########################################");
        System.out.println("Worst Fit");
        WorstFit allocator = new WorstFit(15);

        System.out.println("Tentando alocar dois blocos de tamanho 4");
        Allocation a1 = allocator.allocate(4);
        allocator.allocate(4);
        System.out.println(allocator);

        System.out.println("Liberando o primeiro bloco alocado");
        allocator.deallocate(a1);
        System.out.println(allocator);

        System.out.println("Tentando alocar um bloco de tamanho 8");
        allocator.allocate(8);
        System.out.println("a3 é nulo, pois a pesar de ter 12 espaços disponíveis na memória, " +
                "há apenas 2 espaços um de tamanho 4 cada e o outro de tamanho 7, sendo impossível alocar um bloco de tamanho 8");
        System.out.println(allocator);

        System.out.println("Tentando alocar um bloco de tamanho 3");
        Allocation a4 = allocator.allocate(3);
        System.out.println("como o bloco após a alocação-2 ainda presente é maior, o algorítimo escolhe esse maior bloco e aloca memória");

        System.out.println(allocator);
        System.out.println("########################################\n\n");
    }

    public static void main(String[] args) {
        WorstFitRunner worstFitRunner = new WorstFitRunner();
        worstFitRunner.run();
    }


}
