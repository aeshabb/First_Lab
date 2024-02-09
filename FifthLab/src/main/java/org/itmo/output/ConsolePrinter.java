package org.itmo.output;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ConsolePrinter {
    public void printLine(String lineToPrint) {
        System.out.println(lineToPrint);
    }

    public <T> void printArray(T[] array) {
        for (T arg : array) {
            System.out.println(arg);
        }
    }

    public <T> void printList(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            printLine((i + 1) + ". " + list.get(i));
        }
    }
    public <T> void printSet(Set<T> set) {
        Iterator<T> iter = set.iterator();
        int i = 0;
        while (iter.hasNext()) {
            printLine((i + 1) + ". " + iter.next());
            i++;
        }
    }

}
