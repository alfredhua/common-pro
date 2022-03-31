package com.common.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.common.util.LogUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;

import java.net.InetAddress;

/**
 * es链接数据源
 */
@Slf4j
@Getter
@Setter
public class EsDataSourceInit {

    private volatile ElasticsearchClient client;

    private String clusterName;

    private String clusterNodes;

    private String userName;

    private String password;


    public ElasticsearchClient getClient() {
        if (this.client == null) {
            synchronized (EsDataSourceInit.class) {
                if (this.client == null) {
                    client = createClient();
                }
            }
        }
        return client;
    }

    public ElasticsearchClient createClient(){
        try {
            String[] ipPortArr = clusterNodes.split(",");//逗号分隔
            HttpHost[] httpHosts = new HttpHost[ipPortArr.length];
            for (int i = 0; i < ipPortArr.length; i++) {
                String[] ip_port = ipPortArr[i].split(":");//冒号分隔
                httpHosts[i] = new HttpHost(InetAddress.getByName(ip_port[0]), Integer.parseInt(ip_port[1]), "http");
            }
            RestClient restClient = RestClient.builder(httpHosts).setHttpClientConfigCallback(
                    httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(initCredentialsProvider())).build();
            ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
            return new ElasticsearchClient(transport);
        }catch (Exception e){
            LogUtil.error("create es cluster error",e);
        }
        return null;
    }

    private CredentialsProvider initCredentialsProvider() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(userName, password));
        return credentialsProvider;
    }

}
