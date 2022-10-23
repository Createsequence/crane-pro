package cn.createsequence.crane.core.executor.handler;

import cn.createsequence.crane.core.executor.AssemblyOperationExecution;

/**
 * A processor of {@link AssemblyOperationExecution}
 *
 * @author huangchengxing
 */
public interface AssembleHandler {

    /**
     * Whether the processor supports processing the execution
     *
     * @param execution execution
     * @return true if support process for execution, false otherwise
     */
    boolean support(AssemblyOperationExecution execution);

    /**
     * Processing this execution
     *
     * @param execution execution
     * @return true if continue execution, false otherwise
     */
    boolean process(AssemblyOperationExecution execution);

}
