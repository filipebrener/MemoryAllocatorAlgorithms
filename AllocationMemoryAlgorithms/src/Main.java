import memory.allocator.BestFit;
import memory.allocator.FirstFit;
import memory.allocator.WorstFit;
import memory.model.MemoryAllocator;
import runners.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        System.out.println("É possível rodar cada um dos cenários separadamente dentro de suas classes com sufixo Runner");
        List<Runner> allocators = new ArrayList<>();
        allocators.add(new BestFitRunner());
        allocators.add(new FirstFitRunner());
        allocators.add(new NextFitRunner());
        allocators.add(new QuickFitRunner());
        allocators.add(new WorstFitRunner());
        for(Runner memoryAllocator: allocators){
            memoryAllocator.run();
        }
    }
}