package cn.createsequence.crane.core.parser.operation;

import cn.createsequence.crane.core.util.Sortable;

/**
 * <p>An operation triggered by a specific bean based attribute
 *
 * @author huangchengxing
 * @see AssemblyOperation
 * @see DisassemblyOperation
 */
public interface KeyPropertyOperation extends Sortable {

    /**
     * Get the key property's name of target
     *
     * @return key property's name
     */
    String getKey();

}
