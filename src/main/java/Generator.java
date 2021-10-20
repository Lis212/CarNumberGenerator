import java.io.FileWriter;
import java.io.IOException;

public class Generator implements Runnable {
    private final int START;
    private final int FINISH;
    private final String SOURCE;

    public Generator(int start, int finish, String src) {
        START = start;
        FINISH = finish;
        SOURCE = src;
    }

    public void run() {
        long start = System.currentTimeMillis();
        FileWriter fw = null;
        try {
            fw = new FileWriter(SOURCE, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        StringBuffer buffer = new StringBuffer();

        for (int regionCode = START; regionCode < FINISH; regionCode++) {
            for (int number = 1; number < 1000; number++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            buffer.append(firstLetter)
                                    .append(padNumber(number, 3))
                                    .append(secondLetter)
                                    .append(thirdLetter)
                                    .append(padNumber(regionCode, 2))
                                    .append("\n");
                        }
                    }
                }
            }
            try {
                if (fw != null) {
                    fw.write(buffer.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            buffer.delete(0, buffer.length() - 1);
        }
        try {
            if (fw != null) {
                fw.flush();
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Finish: region: %d - %d, time: %d\n", START, FINISH, System.currentTimeMillis() - start);
    }

    private String padNumber(int number, int numberLength) {
        StringBuilder builder = new StringBuilder();
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            builder.append("0");
        }
        builder.append(number);

        return builder.toString();
    }
}
