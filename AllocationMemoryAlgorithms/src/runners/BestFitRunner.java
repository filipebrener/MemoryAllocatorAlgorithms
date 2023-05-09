package runners;

import memory.allocator.BestFit;
import memory.model.Allocation;

public class BestFitRunner extends Runner{

    public void run(){
        System.out.println("\n\n########################################");
        System.out.println("Best Fit");
        BestFit allocator = new BestFit(15);

        System.out.println("Tentando alocar dois blocos de tamanho 5 ");
        Allocation a1 = allocator.allocate(5);
        Allocation a2 = allocator.allocate(5);
        System.out.println(allocator);

        System.out.println("Liberando o primeiro bloco alocado");
        allocator.deallocate(a1);
        System.out.println(allocator);

        System.out.println("Tentando alocar um bloco de tamanho 7");
        Allocation a3 = allocator.allocate(7);
        System.out.println("a3 é nulo, pois a pesar de ter 10 espaços disponíveis na memória, " +
                "há apenas 2 espaços de tamanho 5 cada, sendo impossível alocar um bloco de tamanho 7");
        System.out.println(allocator);

        System.out.println("Tentando alocar um bloco de tamanho 4");
        Allocation a4 = allocator.allocate(4);
        assert a4 != null;
        System.out.println("como o bloco solicitado é de tamanho 4 foi possível alocar a memória");

        System.out.println(allocator);
        System.out.println("########################################\n\n");
    }
    public static void main(String[] args) {
        BestFitRunner bestFit = new BestFitRunner();
        bestFit.run();
    }


}
