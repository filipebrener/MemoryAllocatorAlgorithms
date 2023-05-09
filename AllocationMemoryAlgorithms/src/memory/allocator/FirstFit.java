package memory.allocator;

import memory.model.Allocation;
import memory.model.MemoryAllocator;

public class FirstFit extends MemoryAllocator {

    public FirstFit(int size) {
        super(size);
    }



    /**
     * Implementação do algoritmo FirstFit para alocação de memória.
     * O algoritmo percorre a memória em busca do primeiro bloco livre que seja grande o suficiente
     * para armazenar o novo processo. Ao encontrar um bloco livre que atenda a essa condição,
     * ele o marca como ocupado e cria uma alocação para o processo.
     * Caso nenhum bloco livre seja encontrado, a alocação falha e retorna nulo.
     *
     * @param size O tamanho do espaço de memória que o processo precisa alocar.
     * @return Uma instância de Allocation ou null caso não seja possível alocar um espaço de memória com o tamanho especificado.
     */
    @Override
    public Allocation allocate(int size) {
        int startIndex = -1;
        int consecutiveFreeSpaces = 0;

        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == 0) {
                if (startIndex == -1) {
                    startIndex = i;
                }
                consecutiveFreeSpaces++;
                if (consecutiveFreeSpaces == size) {
                    Allocation a = new Allocation(startIndex, size);
                    allocations.add(a);
                    for (int j = startIndex; j < startIndex + size; j++) {
                        memory[j] = 1;
                    }
                    return a;
                }
            } else {
                startIndex = -1;
                consecutiveFreeSpaces = 0;
            }
        }

        return null;
    }

}
