package org.apache.beam.examples;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.Validation;

public interface IApiReaderOptions extends PipelineOptions {
    
    @Description("API to read from")
    @Default.String("")
    String getApi();
    
    void setApi(String value);
    
    /**
     * Set this required option to specify where to write the output.
     */
    @Description("Path of the file to write to")
    @Validation.Required
    String getOutput();
    
    void setOutput(String value);
    
}
