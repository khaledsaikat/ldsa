import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import org.junit.Test;

import java.util.Random;

/**
 *
 */
public class DatabaseBenchmarkTest
{
    /*
        Test Results:
        About 300-400 insert operations/second are possible on the ES server from a home internet connection.
        About 200-300 insert operations/second are possible on my local computer.
     */
    @Test
    public void DatabaseBenchmark() throws Exception
    {
        String ip = "134.91.79.24";
        //String ip = "127.0.0.1";

        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        Session session = cluster.connect();

        session.execute("DROP KEYSPACE IF EXISTS benchmarkTest");
        session.execute("CREATE KEYSPACE benchmarkTest WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};");
        session.execute("USE benchmarkTest");
        session.execute("CREATE TABLE test(id INT PRIMARY KEY,guid UUID,coin INT,dice INT,percent INT, random INT);");

        BenchmarkThread thread = new BenchmarkThread();
        thread.rng = new Random();
        thread.prep = session.prepare("INSERT INTO test (id,guid,coin,dice,percent,random) VALUES (?,uuid(),?,?,?,?);");
        thread.session = session;
        thread.insertIterations = 10000;
        thread.selectIterations = 1000;
        thread.start();

        while(!thread.done)
        {
            Thread.sleep(1000);
            System.out.println(String.format("Inserts per second: %d",thread.opsPerSecond));
            thread.opsPerSecond = 0;
        }
        session.execute("DROP KEYSPACE IF EXISTS benchmarkTest");

        //Junit tests do pass if the method ends.
    }

    class BenchmarkThread extends Thread
    {
        public Random rng;
        public PreparedStatement prep;
        public int insertIterations;
        public int selectIterations;
        public Session session;

        private BoundStatement bound;

        public int opsPerSecond;
        public boolean done;

        @Override
        public void run()
        {
            for (int i = 0; i < insertIterations; i++)
            {
                bound = prep.bind(i,rng.nextInt(2),rng.nextInt(6),rng.nextInt(100),rng.nextInt());
                session.execute(bound);
                opsPerSecond++;
            }
            done = true;
        }
    }
}
