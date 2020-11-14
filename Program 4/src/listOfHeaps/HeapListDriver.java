package listOfHeaps;

public class HeapListDriver {

    public static void main(String[] args) {
        ListOfHeaps heaps = new ListOfHeaps();
        heaps.insert(56);
        System.out.println(heaps.toString());
        heaps.insert(100);
        System.out.println(heaps.toString());
        heaps.insert(32);
        System.out.println(heaps.toString());
        heaps.insert(7);
        System.out.println(heaps.toString());
        heaps.insert(84);
        System.out.println(heaps.toString());
        heaps.insert(200);
        System.out.println(heaps.toString());
        heaps.insert(150);
        System.out.println(heaps.toString());
        heaps.insert(50);
        System.out.println(heaps.toString());
        heaps.insert(73);
        System.out.println(heaps.toString());
        heaps.insert(42);
        System.out.println(heaps.toString());
        heaps.insert(12);
        System.out.println(heaps.toString());

        System.out.println("Done inserting to the list of Heaps. Size = " + heaps.getSize());
        System.out.println("Now popping from the Max-Heap.");

        while (heaps.getSize() > 0) {
            System.out.println("Popped " + heaps.pop() + ", size now " + heaps.getSize());
            System.out.println(heaps.toString());
        }
    }

	/* Expected Output:
56
100 + 56
100,56,32
7 + 100,56,32
84 + 7 + 100,56,32
200,7,84 + 100,56,32
200,100,150,56,7,32,84
50 + 200,100,150,56,7,32,84
73 + 50 + 200,100,150,56,7,32,84
73,50,42 + 200,100,150,56,7,32,84
12 + 73,50,42 + 200,100,150,56,7,32,84
Done inserting to the list of Heaps. Size = 11
Now popping from the Max-Heap.
Popped 200, size now 10
12 + 73,50,42 + 150,100,84,56,7,32
Popped 150, size now 9
12 + 73,50,42 + 100,56,84,32,7
Popped 100, size now 8
12 + 73,50,42 + 84,56,7,32
Popped 84, size now 7
12 + 73,50,42 + 56,32,7
Popped 73, size now 6
12 + 50,42 + 56,32,7
Popped 56, size now 5
12 + 50,42 + 32,7
Popped 50, size now 4
12 + 42 + 32,7
Popped 42, size now 3
12 + 32,7
Popped 32, size now 2
12 + 7
Popped 12, size now 1
7
Popped 7, size now 0
<Empty Heap>
	 */

}