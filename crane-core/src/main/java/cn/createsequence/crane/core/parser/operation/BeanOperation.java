package cn.createsequence.crane.core.parser.operation;

import java.util.Collection;

/**
 * @author huangchengxing
 */
public interface BeanOperation {

    /**
     * target type
     *
     * @return target type
     */
    Class<?> getTargetType();

    /**
     * Get all assembly operations on target
     *
     * @return assembly operations
     */
    Collection<AssemblyOperation> getAssemblyOperations();

    /**
     * Get all disassembly operations on target
     *
     * @return disassembly operations
     */
    Collection<DisassemblyOperation> getDisassemblyOperations();

}
