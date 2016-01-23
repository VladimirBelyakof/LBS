import jaxb.general.TimerNode;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Scheduler {
    private SortedSet<TimerNode> currentTimers;
    private static Scheduler instance;
    private Scheduler() {
        Comparator<TimerNode> comparatorTimer = new Comparator<TimerNode>() {
            @Override
            public int compare(TimerNode firstTimerNode, TimerNode secondTimerNode) {
                return firstTimerNode.getRunDate().compareTo(secondTimerNode.getRunDate());
            }
        };
        currentTimers = new TreeSet<>(comparatorTimer);
    }
    public static synchronized Scheduler getInstance() {
        if (instance == null) {
            instance = new Scheduler();
        }
        return instance;
    }

    public boolean add(TimerNode timerNode) {
        synchronized (currentTimers) {
            if (currentTimers.isEmpty()) {
                currentTimers.add(timerNode);

                return true;
            }
            return currentTimers.add(timerNode);
        }
    }
}
