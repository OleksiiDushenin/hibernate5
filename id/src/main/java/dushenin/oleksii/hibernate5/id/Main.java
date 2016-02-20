package dushenin.oleksii.hibernate5.id;

import dushenin.oleksii.hibernate5.id.entity.BookEnhancedSequenceId;
import dushenin.oleksii.hibernate5.id.entity.BookIdentityId;
import dushenin.oleksii.hibernate5.id.entity.BookSequenceId;
import dushenin.oleksii.hibernate5.id.entity.BookTableId;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by alexeydushenin
 * on 2/16/16.
 */
@State(Scope.Benchmark)
public class Main {

    private static final int RECORDS = 10_000;

    private SessionFactory sessionFactory;

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(Main.class.getName())
                .warmupIterations(5)
                .measurementIterations(10)
                .forks(1)
                .build();

        new Runner(options).run();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testIdentityId() {
        runSingleTest(i -> new BookIdentityId("name " + i), this::saveBooks);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testSequenceId() {
        runSingleTest(i -> new BookSequenceId("name " + i), this::saveBooks);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testEnhancedSequenceId() {
        runSingleTest(i -> new BookEnhancedSequenceId("name " + i), this::saveBooks);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testTableId() {
        runSingleTest(i -> new BookTableId("name " + i), this::saveBooks);
    }

    public <T> void saveBooks(List<T> books) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        books.stream().forEach(session::persist);
        transaction.commit();
        session.close();
    }

    public <T> void runSingleTest(Function<Integer, T> supplier, Consumer<List<T>> consumer) {
        List<T> books = IntStream
                .range(0, RECORDS)
                .mapToObj(supplier::apply)
                .collect(Collectors.toList());

        consumer.accept(books);
    }

    @Setup
    public void setUp() {
        sessionFactory = new MetadataSources(
                new StandardServiceRegistryBuilder()
                        .loadProperties("/hibernate.properties")
                        .build()
        )
                .addAnnotatedClass(BookIdentityId.class)
                .addAnnotatedClass(BookSequenceId.class)
                .addAnnotatedClass(BookTableId.class)
                .addAnnotatedClass(BookEnhancedSequenceId.class)
                .buildMetadata().buildSessionFactory();
    }

    @TearDown
    public void tearDown() {
        sessionFactory.close();
    }

}
