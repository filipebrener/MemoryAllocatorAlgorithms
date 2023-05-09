package runners;

import memory.allocator.BestFit;
import memory.allocator.FirstFit;
import memory.allocator.QuickFit;
import memory.model.Allocation;

public class QuickFitRunner extends Runner{

    public void run(){
        System.out.println("\n\n########################################");
        System.out.println("Quick Fit");
        int[] quickFitSizes = {3, 5, 7};
        QuickFit allocator = new QuickFit(15, quickFitSizes);

        System.out.println("Tentando alocar dois blocos de tamanho 5 ");
        Allocation a1 = allocator.allocate(5);
        allocator.allocate(5);
        System.out.println(allocator);

        System.out.println("Liberando o primeiro bloco alocado");
        allocator.deallocate(a1);
        System.out.println(allocator);

        System.out.println("Tentando alocar um bloco de tamanho 7");
        allocator.allocate(7);
        System.out.println("a3 é nulo, pois a pesar de ter 10 espaços disponíveis na memória, " +
                "há apenas 2 espaços de tamanho 5 cada, sendo impossível alocar um bloco de tamanho 7");
        System.out.println(allocator);

        System.out.println("Tentando alocar um bloco de tamanho 3");
        Allocation a4 = allocator.allocate(3);
        System.out.println("como o bloco solicitado é de tamanho 3 foi possível alocar a memória");

        System.out.println(allocator);
        System.out.println("########################################\n\n");
    }
    public static void main(String[] args) {
        QuickFitRunner quickFitRunner = new QuickFitRunner();
        quickFitRunner.run();
    }


}
