package memory.allocator;

import memory.model.Allocation;
import memory.model.MemoryAllocator;

public class WorstFit extends MemoryAllocator {

    public WorstFit(int size) {
        super(size);
    }

    /**
     * Implementação do algoritmo WorstFit para alocação de memória.
     * O algoritmo percorre a memória procurando pelo maior bloco livre que atenda à solicitação de tamanho.
     * Caso encontre, aloca e adiciona à lista de alocações e marca a área como alocada na memória.
     * Caso contrário, retorna nulo indicando falha na alocação.
     */
    @Override
    public Allocation allocate(int size) {
        int max = -1;
        int start = -1;

        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == 0) {
                int j = i;
                while (j < memory.length && memory[j] == 0) {
                    j++;
                }

                if (j - i > max && j - i >= size) {
                    max = j - i;
                    start = i;
                }

                i = j;
            }
        }

        if (start == -1) {
            return null;
        }

        Allocation newAlloc = new Allocation(start, size);
        allocations.add(newAlloc);

        for (int i = start; i < start + size; i++) {
            memory[i] = 1;
        }

        return newAlloc;
    }
}
