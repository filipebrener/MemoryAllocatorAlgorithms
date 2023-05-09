package memory.allocator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import memory.model.MemoryAllocator;
import memory.model.Allocation;

public class QuickFit extends MemoryAllocator {

    private Map<Integer, List<Allocation>> quickFitLists;

    public QuickFit(int size, int[] quickFitSizes) {
        super(size);
        quickFitLists = new HashMap<>();
        for (int quickFitSize : quickFitSizes) {
            quickFitLists.put(quickFitSize, new ArrayList<>());
        }
    }

    /**
     * Implementação do algoritmo QuickFit para alocação de memória.
     * O algoritmo possui uma lista para cada tamanho pré-definido de alocação.
     * Quando uma alocação é solicitada, o algoritmo tenta encontrá-la na lista correspondente ao tamanho.
     * Caso não encontre, percorre a memória procurando por um bloco livre que atenda à solicitação de tamanho.
     * Se encontrar um bloco livre, aloca e adiciona à lista de alocações e marca a área como alocada na memória.
     * Caso contrário, retorna nulo indicando falha na alocação.
     */
    @Override
    public Allocation allocate(int size) {
        List<Allocation> list = quickFitLists.get(size);
        if (list != null && !list.isEmpty()) {
            Allocation a = list.remove(0);
            markMemory(a.getStart(), a.getSize(), 1);
            return a;
        }

        int start = findFreeMemory(size);

        if (start == -1) {
            return null;
        }

        Allocation newAlloc = new Allocation(start, size);
        allocations.add(newAlloc);
        markMemory(start, size, 1);
        return newAlloc;
    }

    private void markMemory(int start, int size, int value) {
        for (int i = start; i < start + size; i++) {
            memory[i] = value;
        }
    }

    private int findFreeMemory(int size) {
        int start = -1;
        int i = 0;

        while (start == -1 && i < memory.length) {
            if (memory[i] == 0 && i + size <= memory.length) {
                int j = i;
                while (j < memory.length && memory[j] == 0 && j - i < size) {
                    j++;
                }

                if (j - i == size) {
                    start = i;
                }

                i = j;
            } else {
                i++;
            }
        }

        return start;
    }
}
