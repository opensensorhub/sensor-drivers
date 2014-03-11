/***************************** BEGIN LICENSE BLOCK ***************************

 The contents of this file are subject to the Mozilla Public License Version
 1.1 (the "License"); you may not use this file except in compliance with
 the License. You may obtain a copy of the License at
 http://www.mozilla.org/MPL/MPL-1.1.html
 
 Software distributed under the License is distributed on an "AS IS" basis,
 WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 for the specific language governing rights and limitations under the License.
 
 The Original Code is "SensorHub".
 
 The Initial Developer of the Original Code is Sensia Software LLC.
 <http://www.sensiasoftware.com>. Portions created by the Initial
 Developer are Copyright (C) 2013 the Initial Developer. All Rights Reserved.
 
 Please contact Alexandre Robin <alex.robin@sensiasoftware.com> for more 
 information.
 
 Contributor(s): 
    Alexandre Robin <alex.robin@sensiasoftware.com>
 
******************************* END LICENSE BLOCK ***************************/

package org.sensorhub.api.sensor;

import java.util.List;
import org.sensorhub.api.common.CommandStatus;
import org.sensorhub.api.common.IEventProducer;
import org.vast.cdm.common.DataBlock;
import org.vast.cdm.common.DataComponent;
import org.vast.util.DateTime;


/**
 * <p>
 * Interface to be implemented by all sensor drivers connected to the system
 * Commands can be sent to each sensor controllable input via this interface.
 * Commands can be executed synchronously or asynchronously by sensors.
 * Implementations of this class can produce events of type SensorControlEvent.
 * </p>
 * 
 * <p>Copyright (c) 2010</p>
 * @author Alexandre Robin
 * @since Nov 5, 2010
 */
public interface ISensorControlInterface extends IEventProducer
{	
    
    /**
     * Allows by-reference access to parent sensor interface
     * @return
     */
    public ISensorInterface<?> getParentSensor();
    
    
    /**
     * Checks if this interface is enabled
     * @return true if interface is enabled, false otherwise
     */
    public boolean isEnabled();
    
    
	/**
	 * Checks asynchronous execution capability 
	 * @return true if asynchronous command execution is supported, false otherwise
	 */
	public boolean isAsyncExecSupported();
	
	
	/**
     * Checks scheduled execution capability 
     * @return true if scheduled command execution is supported, false otherwise
     */
    public boolean isSchedulingSupported();
	    
    
    /**
     * Checks status history capability
     * @return true if status history is supported, false otherwise
     */
    public boolean isStatusHistorySupported();
	
	
	/**
	 * Retrieves description of command message
	 * Note that this can be a choice of multiple messages
	 * @return
	 */
	public DataComponent getCommandDescription();
	
	
	/**
	 * Executes the command synchronously, blocking until completion of command
	 * @param command
	 * @return
	 * @throws SensorException
	 */
	public CommandStatus execCommand(DataBlock command) throws SensorException;
	
	
	/**
     * Executes multiple commands synchronously and in the order specified.
     * This method will block until all commands are completed
     * @param commands
     * @return a single status message for the command group
     * @throws SensorException
     */
    public CommandStatus execCommandGroup(List<DataBlock> commands) throws SensorException;
    
    
	/**
	 * Sends a command that will be executed asynchronously
	 * @see #isAsyncExecSupported()
	 * @param command
	 * @return status of the command
     * @throws SensorException
	 */
	public CommandStatus sendCommand(DataBlock command) throws SensorException;
	
	
	/**
	 * Sends a group of commands for asynchronous execution.
	 * Order is guaranteed but not atomicity
	 * @see #isAsyncExecSupported()
	 * @param commands
	 * @return a single status object for the command group
     * @throws SensorException
	 */
	public CommandStatus sendCommandGroup(List<DataBlock> commands) throws SensorException;
		
	
	/**
	 * Schedules a command to be executed asynchronously at the specified time
	 * @see #isSchedulingSupported()
	 * @param command
	 * @param execTime
	 * @return
     * @throws SensorException
	 */
	public CommandStatus scheduleCommand(DataBlock command, DateTime execTime) throws SensorException;
	
	
	/**
     * Schedules a group of commands to be executed asynchronously at the specified time.
     * Order is guaranteed but not atomicity
     * @see #isSchedulingSupported()
     * @param commands
     * @param execTime
     * @return a single status object for the command group
     * @throws SensorException
     */
    public CommandStatus scheduleCommandGroup(List<DataBlock> commands, DateTime execTime) throws SensorException;
		
	
	/**
	 * Cancels a command before it is executed (for async or scheduled commands)
	 * @see #isAsyncExecSupported()
	 * @param commandID
	 * @return status of the cancelled command
     * @throws SensorException
	 */
	public CommandStatus cancelCommand(String commandID) throws SensorException;
	
	
	/**
     * @param commandID
     * @see #isAsyncExecSupported()
     * @return current status of the command with the specified ID
     * @throws SensorException
     */
    public CommandStatus getCommandStatus(String commandID) throws SensorException;
    
    
    /**
     * Gets complete status history for the specified command
     * @see #isStatusHistorySupported()
     * @param commandID
     * @return
     * @throws SensorException
     */
    public List<CommandStatus> getCommandStatusHistory(String commandID) throws SensorException;

}