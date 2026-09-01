package Walmart;

import java.util.ArrayList;
import java.util.List;

public class PowerOfTwoMaxHeap {

    private final List<Integer> heap;
    private final int branchingPower;
    private final int childrenPerNode;

    public PowerOfTwoMaxHeap(int branchingPower) {
        if (branchingPower < 0 || branchingPower > 30) {
            throw new IllegalArgumentException(
                "branchingPower must be between 0 and 30"
            );
        }

        this.branchingPower = branchingPower;
        this.childrenPerNode = 1 << branchingPower;
        this.heap = new ArrayList<>();
    }

    public void insert(int value) {
        heap.add(value);

        int currentIndex = heap.size() - 1;

        // Move the new element upward until heap property is restored.
        while (currentIndex > 0) {
            int parentIndex =
                    (currentIndex - 1) >> branchingPower;

            if (heap.get(parentIndex) >= heap.get(currentIndex)) {
                break;
            }

            swap(parentIndex, currentIndex);
            currentIndex = parentIndex;
        }
    }

    public int popMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        int maximum = heap.get(0);
        int lastIndex = heap.size() - 1;

        // Only one element.
        if (lastIndex == 0) {
            heap.remove(lastIndex);
            return maximum;
        }

        // Move the last element to the root.
        heap.set(0, heap.remove(lastIndex));

        int currentIndex = 0;

        // Move root downward until heap property is restored.
        while (true) {

            int firstChildIndex =
                    (currentIndex << branchingPower) + 1;

            // No children.
            if (firstChildIndex >= heap.size()) {
                break;
            }

            int lastChildIndex = Math.min(
                    firstChildIndex + childrenPerNode - 1,
                    heap.size() - 1
            );

            int largestChildIndex = firstChildIndex;

            // Find the largest child.
            for (int childIndex = firstChildIndex + 1;
                 childIndex <= lastChildIndex;
                 childIndex++) {

                if (heap.get(childIndex) >
                        heap.get(largestChildIndex)) {

                    largestChildIndex = childIndex;
                }
            }

            // Current node is already larger than every child.
            if (heap.get(currentIndex) >=
                    heap.get(largestChildIndex)) {
                break;
            }

            swap(currentIndex, largestChildIndex);
            currentIndex = largestChildIndex;
        }

        return maximum;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    private void swap(int firstIndex, int secondIndex) {
        int temporary = heap.get(firstIndex);
        heap.set(firstIndex, heap.get(secondIndex));
        heap.set(secondIndex, temporary);
    }

    public static void main(String[] args) {

        // x = 1 -> 2 children per node
        PowerOfTwoMaxHeap binaryHeap =
                new PowerOfTwoMaxHeap(1);

        binaryHeap.insert(10);
        binaryHeap.insert(30);
        binaryHeap.insert(20);
        binaryHeap.insert(5);
        binaryHeap.insert(40);

        System.out.println(binaryHeap.popMax()); // 40
        System.out.println(binaryHeap.popMax()); // 30
        System.out.println(binaryHeap.popMax()); // 20


        // x = 3 -> 8 children per node
        PowerOfTwoMaxHeap eightWayHeap =
                new PowerOfTwoMaxHeap(3);

        for (int i = 1; i <= 20; i++) {
            eightWayHeap.insert(i);
        }

        System.out.println("\n8-way heap:");

        while (!eightWayHeap.isEmpty()) {
            System.out.print(eightWayHeap.popMax() + " ");
        }
    }
}