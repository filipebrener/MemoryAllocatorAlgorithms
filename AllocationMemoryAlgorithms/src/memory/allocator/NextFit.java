package memory.allocator;

import memory.model.Allocation;
import memory.model.MemoryAllocator;

public class NextFit extends MemoryAllocator {

    private int lastAllocatedIndex;

    public NextFit(int size) {
        super(size);
    }

    /**
     * Implementação do algoritmo NextFit para alocação de memória.
     * O algoritmo começa a procurar por um bloco livre a partir da posição da última alocação bem-sucedida.
     * Ele percorre a lista de alocações circularmente, buscando por um bloco livre que seja grande o suficiente
     * para armazenar o novo processo. Ao encontrar um bloco livre que atenda a essa condição,
     * ele o marca como ocupado e cria uma alocação para o processo.
     * Caso nenhum bloco livre seja encontrado, a alocação falha e retorna nulo.
     */
    @Override
    public Allocation allocate(int size) {
        int startIndex = -1;
        int consecutiveFreeSpaces = 0;
        boolean alreadyBackToStart = false;
        int inicialIndex = (lastAllocatedIndex == memory.length) ? 0 : lastAllocatedIndex;
        for (int i = inicialIndex; i < memory.length; i++) {
            if (memory[i] == 0) {
                if (startIndex == -1) {
                    startIndex = i;
                }
                consecutiveFreeSpaces++;
                if (consecutiveFreeSpaces == size) {
                    Allocation a = new Allocation(startIndex, size);
                    allocations.add(a);
                    for (lastAllocatedIndex = startIndex; lastAllocatedIndex < startIndex + size; lastAllocatedIndex++) {
                        memory[lastAllocatedIndex] = 1;
                    }
                    return a;
                }
            } else {
                startIndex = -1;
                consecutiveFreeSpaces = 0;
            }
            if(i + 1 == memory.length && !alreadyBackToStart){
                i = -1;
                alreadyBackToStart = true;
                startIndex = -1;
                consecutiveFreeSpaces = 0;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        String className = getClass().getSimpleName();
        int allocatedSize = allocations.stream().mapToInt(Allocation::getSize).sum();
        int freeSize = memory.length - allocatedSize;
        return String.format("%s - Memoria Total: %d, Alocado: %d, Disponível: %d, Índice da última alocação: %s\n%s", className, memory.length, allocatedSize, freeSize, lastAllocatedIndex, getMemoryPrint());
    }


}
