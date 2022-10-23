package cn.createsequence.crane.core.executor;

import cn.createsequence.crane.core.parser.operation.AssemblyOperation;

import java.util.Collection;

/**
 * <p>An assembly operate execution,
 * consisting of target objects and {@link AssemblyOperation}.
 *
 * @author huangchengxing
 */
public interface AssemblyOperationExecution {

    /**
     * Get the assembly operation required for this execution
     *
     * @return Assembly operation
     */
    AssemblyOperation getOperation();

    /**
     * Get the target objects to be processed for this execution
     *
     * @return target objects
     */
    Collection<?> getTargetObjects();

}
