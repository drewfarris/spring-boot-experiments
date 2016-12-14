package drew.app.util;


import org.apache.accumulo.minicluster.MiniAccumuloCluster;
import org.apache.accumulo.minicluster.impl.MiniAccumuloClusterImpl;
import org.apache.accumulo.minicluster.impl.MiniAccumuloConfigImpl;
import org.apache.commons.io.FileUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;

/** A simple Bean that will start and stop a MiniAccumuloCluster */
public class MiniAccumuloBean {

    private final File tempDir;
    private final String password;
    private MiniAccumuloClusterImpl cluster;
    private String zookeepers;
    private String instance;

    public MiniAccumuloBean(File tempDir, String password) {
        this.tempDir = tempDir;
        this.password = password;
    }

    @PostConstruct
    public void init() throws IOException, InterruptedException {
        MiniAccumuloConfigImpl config = new MiniAccumuloConfigImpl(tempDir, password);
        config.setZooKeeperStartupTime(100000);
        cluster = new MiniAccumuloClusterImpl(config);
        zookeepers = cluster.getZooKeepers();
        instance = cluster.getInstanceName();
        cluster.start();
    }

    @PreDestroy
    public void destroy() throws IOException, InterruptedException {
        cluster.stop();
        FileUtils.deleteQuietly(tempDir);
    }

    public MiniAccumuloClusterImpl getCluster() {
        return cluster;
    }

    public String getZookeepers() {
        return zookeepers;
    }

    public String getInstance() {
        return instance;
    }

    public File getTempDir() {
        return tempDir;
    }

    public String getPassword() {
        return password;
    }

}
