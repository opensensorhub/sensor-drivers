/***************************** BEGIN LICENSE BLOCK ***************************

The contents of this file are subject to the Mozilla Public License, v. 2.0.
If a copy of the MPL was not distributed with this file, You can obtain one
at http://mozilla.org/MPL/2.0/.

Software distributed under the License is distributed on an "AS IS" basis,
WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
for the specific language governing rights and limitations under the License.
 
The Initial Developer is Sensia Software LLC. Portions created by the Initial
Developer are Copyright (C) 2014 the Initial Developer. All Rights Reserved.
 
******************************* END LICENSE BLOCK ***************************/

package org.sensorhub.api.sensor;

import net.opengis.swe.v20.DataBlock;
import net.opengis.swe.v20.DataComponent;


/**
 * <p>
 * Type of event generated when new data is avaible from sensors.
 * It is immutable and carries sensor data by reference
 * </p>
 *
 * <p>Copyright (c) 2010</p>
 * @author Alexandre Robin
 * @since Nov 5, 2010
 */
public class SensorDataEvent extends SensorEvent
{
	private static final long serialVersionUID = 2124599187504793797L;
    
	
	/**
	 * Description of data records contained in this event (by reference) 
	 */
	protected DataComponent recordDescription;
	
	
	/**
	 * New data that triggered this event
	 */
	protected DataBlock record;
	
	
	/**
	 * Constructor from list of records with their descriptor
	 * @param timeStamp time of event generation
     * @param dataInterface sensor output interface that produced the associated data
	 * @param record new record of data produced at that time
	 */
	public SensorDataEvent(double timeStamp, ISensorDataInterface dataInterface, DataBlock record)
	{
		super(timeStamp, dataInterface.getParentSensor().getLocalID(), Type.NEW_DATA_AVAILABLE);
		this.source = dataInterface;
		this.recordDescription = dataInterface.getRecordDescription();
		this.record = record;
	}


    public DataComponent getRecordDescription()
    {
        return recordDescription;
    }


    public DataBlock getRecord()
    {
        return record;
    }
}