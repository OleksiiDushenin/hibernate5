package dushenin.oleksii.hibernate5.id.repository;

import dushenin.oleksii.hibernate5.id.entity.BookIdentityId;
import dushenin.oleksii.hibernate5.id.entity.BookSequenceId;
import dushenin.oleksii.hibernate5.id.entity.BookTableId;
import dushenin.oleksii.hibernate5.id.service.IdGenerationService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by alexeydushenin
 * on 2/16/16.
 */
public class TableGenerationTypeTest extends IntegrationTestBase {

    private final int SET = 1;
    private final int RECORDS = 10_000;

    @Autowired
    private IdGenerationService idGenerationService;

    @Test
    public void testStrategy() {
        System.out.printf("DURATION IDENTITY: %d%n",
                runTest(i -> new BookIdentityId("name " + i), idGenerationService::saveBooksIdentityId));
        System.out.printf("DURATION SEQUENCE: %d%n",
                runTest(i -> new BookSequenceId("name " + i), idGenerationService::saveBooksSequenceId));
        System.out.printf("DURATION TABLE: %d%n",
                runTest(i -> new BookTableId("name " + i), idGenerationService::saveBooksTableId));
    }

    public <T> long runTest(Function<Integer, T> supplier, Consumer<List<T>> consumer) {
        long time = Long.MAX_VALUE;

        for (int i = 0; i < SET; i++) {
            time = Long.min(time, runSingleTest(supplier, consumer));
        }

        return time;
    }

    public <T> long runSingleTest(Function<Integer, T> supplier, Consumer<List<T>> consumer) {
        List<T> books = IntStream
                .range(0, RECORDS)
                .mapToObj(supplier::apply)
                .collect(Collectors.toList());

        long start = System.currentTimeMillis();
        consumer.accept(books);
        return System.currentTimeMillis() - start;
    }

}
