package org.apache.beam.examples;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.FileIO;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;

import java.util.ArrayList;
import java.util.List;

public class CoinixaApiReader {
    
    public static void main(String[] args) {
        IApiReaderOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().as(IApiReaderOptions.class);
        runApiReader(options);
    }
    
    private static void runApiReader(IApiReaderOptions options) {
        List<String> apis = new ArrayList<>();
        int i = 1;
        while (apis.size() < 3) {
            String coins = "BTC ETH XYZ "+ i;
            
            options.setApi(coins);
            
            Pipeline pipeline = Pipeline.create(options);
    
            pipeline.apply("ReadApiObject", Create.of(coins)).apply("WriteApi", TextIO.write().to(options.getOutput()));
            
            pipeline.run().waitUntilFinish();
            apis.add(coins);
            i++;
        }
    }
}
// mvn -Pdataflow-runner compile exec:java -Dexec.mainClass=org.apache.beam.examples.CoinixaApiReader -Dexec.args="--project=sixth-oath-339016 --gcpTempLocation=gs://coinixa/temp/ --output=gs://coinixa/results/output --runner=DataflowRunner --region=europe-west3" -Pdataflow-runner
