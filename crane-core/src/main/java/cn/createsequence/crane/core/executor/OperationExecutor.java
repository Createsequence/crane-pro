package cn.createsequence.crane.core.executor;

import cn.createsequence.crane.core.parser.operation.AssemblyOperation;
import cn.createsequence.crane.core.parser.operation.BeanOperation;

import java.util.Collection;

/**
 * <p>An executor used to complete the operation for the target object.
 *
 * @author huangchengxing
 * @see AssemblyOperation
 * @see cn.createsequence.crane.core.executor.handler.AssembleHandler
 */
public interface OperationExecutor {

    /**
     * <p>According to the configuration of bean operations,
     * performs operations on each element in the input object collection.
     *
     * @param targets targets
     * @param operations operations
     */
    void execute(Collection<?> targets, BeanOperation operations);

}
