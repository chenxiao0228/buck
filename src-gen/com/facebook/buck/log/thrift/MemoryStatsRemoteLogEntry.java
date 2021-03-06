/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.facebook.buck.log.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)")
public class MemoryStatsRemoteLogEntry implements org.apache.thrift.TBase<MemoryStatsRemoteLogEntry, MemoryStatsRemoteLogEntry._Fields>, java.io.Serializable, Cloneable, Comparable<MemoryStatsRemoteLogEntry> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("MemoryStatsRemoteLogEntry");

  private static final org.apache.thrift.protocol.TField TIME_FROM_START_OF_COMMAND_MS_FIELD_DESC = new org.apache.thrift.protocol.TField("timeFromStartOfCommandMs", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField FREE_MEMORY_BYTES_FIELD_DESC = new org.apache.thrift.protocol.TField("freeMemoryBytes", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField TOTAL_MEMORY_BYTES_FIELD_DESC = new org.apache.thrift.protocol.TField("totalMemoryBytes", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField TIME_SPENT_IN_GC_MS_FIELD_DESC = new org.apache.thrift.protocol.TField("timeSpentInGcMs", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField CURRENT_MEMORY_BYTES_USAGE_BY_POOL_FIELD_DESC = new org.apache.thrift.protocol.TField("currentMemoryBytesUsageByPool", org.apache.thrift.protocol.TType.MAP, (short)5);
  private static final org.apache.thrift.protocol.TField MAX_MEMORY_BYTES_FIELD_DESC = new org.apache.thrift.protocol.TField("maxMemoryBytes", org.apache.thrift.protocol.TType.I64, (short)6);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new MemoryStatsRemoteLogEntryStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new MemoryStatsRemoteLogEntryTupleSchemeFactory();

  public long timeFromStartOfCommandMs; // optional
  public long freeMemoryBytes; // optional
  public long totalMemoryBytes; // optional
  public long timeSpentInGcMs; // optional
  public java.util.Map<java.lang.String,java.lang.Long> currentMemoryBytesUsageByPool; // optional
  public long maxMemoryBytes; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TIME_FROM_START_OF_COMMAND_MS((short)1, "timeFromStartOfCommandMs"),
    FREE_MEMORY_BYTES((short)2, "freeMemoryBytes"),
    TOTAL_MEMORY_BYTES((short)3, "totalMemoryBytes"),
    TIME_SPENT_IN_GC_MS((short)4, "timeSpentInGcMs"),
    CURRENT_MEMORY_BYTES_USAGE_BY_POOL((short)5, "currentMemoryBytesUsageByPool"),
    MAX_MEMORY_BYTES((short)6, "maxMemoryBytes");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // TIME_FROM_START_OF_COMMAND_MS
          return TIME_FROM_START_OF_COMMAND_MS;
        case 2: // FREE_MEMORY_BYTES
          return FREE_MEMORY_BYTES;
        case 3: // TOTAL_MEMORY_BYTES
          return TOTAL_MEMORY_BYTES;
        case 4: // TIME_SPENT_IN_GC_MS
          return TIME_SPENT_IN_GC_MS;
        case 5: // CURRENT_MEMORY_BYTES_USAGE_BY_POOL
          return CURRENT_MEMORY_BYTES_USAGE_BY_POOL;
        case 6: // MAX_MEMORY_BYTES
          return MAX_MEMORY_BYTES;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __TIMEFROMSTARTOFCOMMANDMS_ISSET_ID = 0;
  private static final int __FREEMEMORYBYTES_ISSET_ID = 1;
  private static final int __TOTALMEMORYBYTES_ISSET_ID = 2;
  private static final int __TIMESPENTINGCMS_ISSET_ID = 3;
  private static final int __MAXMEMORYBYTES_ISSET_ID = 4;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.TIME_FROM_START_OF_COMMAND_MS,_Fields.FREE_MEMORY_BYTES,_Fields.TOTAL_MEMORY_BYTES,_Fields.TIME_SPENT_IN_GC_MS,_Fields.CURRENT_MEMORY_BYTES_USAGE_BY_POOL,_Fields.MAX_MEMORY_BYTES};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TIME_FROM_START_OF_COMMAND_MS, new org.apache.thrift.meta_data.FieldMetaData("timeFromStartOfCommandMs", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.FREE_MEMORY_BYTES, new org.apache.thrift.meta_data.FieldMetaData("freeMemoryBytes", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.TOTAL_MEMORY_BYTES, new org.apache.thrift.meta_data.FieldMetaData("totalMemoryBytes", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.TIME_SPENT_IN_GC_MS, new org.apache.thrift.meta_data.FieldMetaData("timeSpentInGcMs", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.CURRENT_MEMORY_BYTES_USAGE_BY_POOL, new org.apache.thrift.meta_data.FieldMetaData("currentMemoryBytesUsageByPool", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64))));
    tmpMap.put(_Fields.MAX_MEMORY_BYTES, new org.apache.thrift.meta_data.FieldMetaData("maxMemoryBytes", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(MemoryStatsRemoteLogEntry.class, metaDataMap);
  }

  public MemoryStatsRemoteLogEntry() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public MemoryStatsRemoteLogEntry(MemoryStatsRemoteLogEntry other) {
    __isset_bitfield = other.__isset_bitfield;
    this.timeFromStartOfCommandMs = other.timeFromStartOfCommandMs;
    this.freeMemoryBytes = other.freeMemoryBytes;
    this.totalMemoryBytes = other.totalMemoryBytes;
    this.timeSpentInGcMs = other.timeSpentInGcMs;
    if (other.isSetCurrentMemoryBytesUsageByPool()) {
      java.util.Map<java.lang.String,java.lang.Long> __this__currentMemoryBytesUsageByPool = new java.util.HashMap<java.lang.String,java.lang.Long>(other.currentMemoryBytesUsageByPool);
      this.currentMemoryBytesUsageByPool = __this__currentMemoryBytesUsageByPool;
    }
    this.maxMemoryBytes = other.maxMemoryBytes;
  }

  public MemoryStatsRemoteLogEntry deepCopy() {
    return new MemoryStatsRemoteLogEntry(this);
  }

  @Override
  public void clear() {
    setTimeFromStartOfCommandMsIsSet(false);
    this.timeFromStartOfCommandMs = 0;
    setFreeMemoryBytesIsSet(false);
    this.freeMemoryBytes = 0;
    setTotalMemoryBytesIsSet(false);
    this.totalMemoryBytes = 0;
    setTimeSpentInGcMsIsSet(false);
    this.timeSpentInGcMs = 0;
    this.currentMemoryBytesUsageByPool = null;
    setMaxMemoryBytesIsSet(false);
    this.maxMemoryBytes = 0;
  }

  public long getTimeFromStartOfCommandMs() {
    return this.timeFromStartOfCommandMs;
  }

  public MemoryStatsRemoteLogEntry setTimeFromStartOfCommandMs(long timeFromStartOfCommandMs) {
    this.timeFromStartOfCommandMs = timeFromStartOfCommandMs;
    setTimeFromStartOfCommandMsIsSet(true);
    return this;
  }

  public void unsetTimeFromStartOfCommandMs() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TIMEFROMSTARTOFCOMMANDMS_ISSET_ID);
  }

  /** Returns true if field timeFromStartOfCommandMs is set (has been assigned a value) and false otherwise */
  public boolean isSetTimeFromStartOfCommandMs() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TIMEFROMSTARTOFCOMMANDMS_ISSET_ID);
  }

  public void setTimeFromStartOfCommandMsIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TIMEFROMSTARTOFCOMMANDMS_ISSET_ID, value);
  }

  public long getFreeMemoryBytes() {
    return this.freeMemoryBytes;
  }

  public MemoryStatsRemoteLogEntry setFreeMemoryBytes(long freeMemoryBytes) {
    this.freeMemoryBytes = freeMemoryBytes;
    setFreeMemoryBytesIsSet(true);
    return this;
  }

  public void unsetFreeMemoryBytes() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __FREEMEMORYBYTES_ISSET_ID);
  }

  /** Returns true if field freeMemoryBytes is set (has been assigned a value) and false otherwise */
  public boolean isSetFreeMemoryBytes() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __FREEMEMORYBYTES_ISSET_ID);
  }

  public void setFreeMemoryBytesIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __FREEMEMORYBYTES_ISSET_ID, value);
  }

  public long getTotalMemoryBytes() {
    return this.totalMemoryBytes;
  }

  public MemoryStatsRemoteLogEntry setTotalMemoryBytes(long totalMemoryBytes) {
    this.totalMemoryBytes = totalMemoryBytes;
    setTotalMemoryBytesIsSet(true);
    return this;
  }

  public void unsetTotalMemoryBytes() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TOTALMEMORYBYTES_ISSET_ID);
  }

  /** Returns true if field totalMemoryBytes is set (has been assigned a value) and false otherwise */
  public boolean isSetTotalMemoryBytes() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TOTALMEMORYBYTES_ISSET_ID);
  }

  public void setTotalMemoryBytesIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TOTALMEMORYBYTES_ISSET_ID, value);
  }

  public long getTimeSpentInGcMs() {
    return this.timeSpentInGcMs;
  }

  public MemoryStatsRemoteLogEntry setTimeSpentInGcMs(long timeSpentInGcMs) {
    this.timeSpentInGcMs = timeSpentInGcMs;
    setTimeSpentInGcMsIsSet(true);
    return this;
  }

  public void unsetTimeSpentInGcMs() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TIMESPENTINGCMS_ISSET_ID);
  }

  /** Returns true if field timeSpentInGcMs is set (has been assigned a value) and false otherwise */
  public boolean isSetTimeSpentInGcMs() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TIMESPENTINGCMS_ISSET_ID);
  }

  public void setTimeSpentInGcMsIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TIMESPENTINGCMS_ISSET_ID, value);
  }

  public int getCurrentMemoryBytesUsageByPoolSize() {
    return (this.currentMemoryBytesUsageByPool == null) ? 0 : this.currentMemoryBytesUsageByPool.size();
  }

  public void putToCurrentMemoryBytesUsageByPool(java.lang.String key, long val) {
    if (this.currentMemoryBytesUsageByPool == null) {
      this.currentMemoryBytesUsageByPool = new java.util.HashMap<java.lang.String,java.lang.Long>();
    }
    this.currentMemoryBytesUsageByPool.put(key, val);
  }

  public java.util.Map<java.lang.String,java.lang.Long> getCurrentMemoryBytesUsageByPool() {
    return this.currentMemoryBytesUsageByPool;
  }

  public MemoryStatsRemoteLogEntry setCurrentMemoryBytesUsageByPool(java.util.Map<java.lang.String,java.lang.Long> currentMemoryBytesUsageByPool) {
    this.currentMemoryBytesUsageByPool = currentMemoryBytesUsageByPool;
    return this;
  }

  public void unsetCurrentMemoryBytesUsageByPool() {
    this.currentMemoryBytesUsageByPool = null;
  }

  /** Returns true if field currentMemoryBytesUsageByPool is set (has been assigned a value) and false otherwise */
  public boolean isSetCurrentMemoryBytesUsageByPool() {
    return this.currentMemoryBytesUsageByPool != null;
  }

  public void setCurrentMemoryBytesUsageByPoolIsSet(boolean value) {
    if (!value) {
      this.currentMemoryBytesUsageByPool = null;
    }
  }

  public long getMaxMemoryBytes() {
    return this.maxMemoryBytes;
  }

  public MemoryStatsRemoteLogEntry setMaxMemoryBytes(long maxMemoryBytes) {
    this.maxMemoryBytes = maxMemoryBytes;
    setMaxMemoryBytesIsSet(true);
    return this;
  }

  public void unsetMaxMemoryBytes() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __MAXMEMORYBYTES_ISSET_ID);
  }

  /** Returns true if field maxMemoryBytes is set (has been assigned a value) and false otherwise */
  public boolean isSetMaxMemoryBytes() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __MAXMEMORYBYTES_ISSET_ID);
  }

  public void setMaxMemoryBytesIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __MAXMEMORYBYTES_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case TIME_FROM_START_OF_COMMAND_MS:
      if (value == null) {
        unsetTimeFromStartOfCommandMs();
      } else {
        setTimeFromStartOfCommandMs((java.lang.Long)value);
      }
      break;

    case FREE_MEMORY_BYTES:
      if (value == null) {
        unsetFreeMemoryBytes();
      } else {
        setFreeMemoryBytes((java.lang.Long)value);
      }
      break;

    case TOTAL_MEMORY_BYTES:
      if (value == null) {
        unsetTotalMemoryBytes();
      } else {
        setTotalMemoryBytes((java.lang.Long)value);
      }
      break;

    case TIME_SPENT_IN_GC_MS:
      if (value == null) {
        unsetTimeSpentInGcMs();
      } else {
        setTimeSpentInGcMs((java.lang.Long)value);
      }
      break;

    case CURRENT_MEMORY_BYTES_USAGE_BY_POOL:
      if (value == null) {
        unsetCurrentMemoryBytesUsageByPool();
      } else {
        setCurrentMemoryBytesUsageByPool((java.util.Map<java.lang.String,java.lang.Long>)value);
      }
      break;

    case MAX_MEMORY_BYTES:
      if (value == null) {
        unsetMaxMemoryBytes();
      } else {
        setMaxMemoryBytes((java.lang.Long)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case TIME_FROM_START_OF_COMMAND_MS:
      return getTimeFromStartOfCommandMs();

    case FREE_MEMORY_BYTES:
      return getFreeMemoryBytes();

    case TOTAL_MEMORY_BYTES:
      return getTotalMemoryBytes();

    case TIME_SPENT_IN_GC_MS:
      return getTimeSpentInGcMs();

    case CURRENT_MEMORY_BYTES_USAGE_BY_POOL:
      return getCurrentMemoryBytesUsageByPool();

    case MAX_MEMORY_BYTES:
      return getMaxMemoryBytes();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case TIME_FROM_START_OF_COMMAND_MS:
      return isSetTimeFromStartOfCommandMs();
    case FREE_MEMORY_BYTES:
      return isSetFreeMemoryBytes();
    case TOTAL_MEMORY_BYTES:
      return isSetTotalMemoryBytes();
    case TIME_SPENT_IN_GC_MS:
      return isSetTimeSpentInGcMs();
    case CURRENT_MEMORY_BYTES_USAGE_BY_POOL:
      return isSetCurrentMemoryBytesUsageByPool();
    case MAX_MEMORY_BYTES:
      return isSetMaxMemoryBytes();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof MemoryStatsRemoteLogEntry)
      return this.equals((MemoryStatsRemoteLogEntry)that);
    return false;
  }

  public boolean equals(MemoryStatsRemoteLogEntry that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_timeFromStartOfCommandMs = true && this.isSetTimeFromStartOfCommandMs();
    boolean that_present_timeFromStartOfCommandMs = true && that.isSetTimeFromStartOfCommandMs();
    if (this_present_timeFromStartOfCommandMs || that_present_timeFromStartOfCommandMs) {
      if (!(this_present_timeFromStartOfCommandMs && that_present_timeFromStartOfCommandMs))
        return false;
      if (this.timeFromStartOfCommandMs != that.timeFromStartOfCommandMs)
        return false;
    }

    boolean this_present_freeMemoryBytes = true && this.isSetFreeMemoryBytes();
    boolean that_present_freeMemoryBytes = true && that.isSetFreeMemoryBytes();
    if (this_present_freeMemoryBytes || that_present_freeMemoryBytes) {
      if (!(this_present_freeMemoryBytes && that_present_freeMemoryBytes))
        return false;
      if (this.freeMemoryBytes != that.freeMemoryBytes)
        return false;
    }

    boolean this_present_totalMemoryBytes = true && this.isSetTotalMemoryBytes();
    boolean that_present_totalMemoryBytes = true && that.isSetTotalMemoryBytes();
    if (this_present_totalMemoryBytes || that_present_totalMemoryBytes) {
      if (!(this_present_totalMemoryBytes && that_present_totalMemoryBytes))
        return false;
      if (this.totalMemoryBytes != that.totalMemoryBytes)
        return false;
    }

    boolean this_present_timeSpentInGcMs = true && this.isSetTimeSpentInGcMs();
    boolean that_present_timeSpentInGcMs = true && that.isSetTimeSpentInGcMs();
    if (this_present_timeSpentInGcMs || that_present_timeSpentInGcMs) {
      if (!(this_present_timeSpentInGcMs && that_present_timeSpentInGcMs))
        return false;
      if (this.timeSpentInGcMs != that.timeSpentInGcMs)
        return false;
    }

    boolean this_present_currentMemoryBytesUsageByPool = true && this.isSetCurrentMemoryBytesUsageByPool();
    boolean that_present_currentMemoryBytesUsageByPool = true && that.isSetCurrentMemoryBytesUsageByPool();
    if (this_present_currentMemoryBytesUsageByPool || that_present_currentMemoryBytesUsageByPool) {
      if (!(this_present_currentMemoryBytesUsageByPool && that_present_currentMemoryBytesUsageByPool))
        return false;
      if (!this.currentMemoryBytesUsageByPool.equals(that.currentMemoryBytesUsageByPool))
        return false;
    }

    boolean this_present_maxMemoryBytes = true && this.isSetMaxMemoryBytes();
    boolean that_present_maxMemoryBytes = true && that.isSetMaxMemoryBytes();
    if (this_present_maxMemoryBytes || that_present_maxMemoryBytes) {
      if (!(this_present_maxMemoryBytes && that_present_maxMemoryBytes))
        return false;
      if (this.maxMemoryBytes != that.maxMemoryBytes)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetTimeFromStartOfCommandMs()) ? 131071 : 524287);
    if (isSetTimeFromStartOfCommandMs())
      hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(timeFromStartOfCommandMs);

    hashCode = hashCode * 8191 + ((isSetFreeMemoryBytes()) ? 131071 : 524287);
    if (isSetFreeMemoryBytes())
      hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(freeMemoryBytes);

    hashCode = hashCode * 8191 + ((isSetTotalMemoryBytes()) ? 131071 : 524287);
    if (isSetTotalMemoryBytes())
      hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(totalMemoryBytes);

    hashCode = hashCode * 8191 + ((isSetTimeSpentInGcMs()) ? 131071 : 524287);
    if (isSetTimeSpentInGcMs())
      hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(timeSpentInGcMs);

    hashCode = hashCode * 8191 + ((isSetCurrentMemoryBytesUsageByPool()) ? 131071 : 524287);
    if (isSetCurrentMemoryBytesUsageByPool())
      hashCode = hashCode * 8191 + currentMemoryBytesUsageByPool.hashCode();

    hashCode = hashCode * 8191 + ((isSetMaxMemoryBytes()) ? 131071 : 524287);
    if (isSetMaxMemoryBytes())
      hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(maxMemoryBytes);

    return hashCode;
  }

  @Override
  public int compareTo(MemoryStatsRemoteLogEntry other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetTimeFromStartOfCommandMs()).compareTo(other.isSetTimeFromStartOfCommandMs());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTimeFromStartOfCommandMs()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.timeFromStartOfCommandMs, other.timeFromStartOfCommandMs);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetFreeMemoryBytes()).compareTo(other.isSetFreeMemoryBytes());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFreeMemoryBytes()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.freeMemoryBytes, other.freeMemoryBytes);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTotalMemoryBytes()).compareTo(other.isSetTotalMemoryBytes());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTotalMemoryBytes()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.totalMemoryBytes, other.totalMemoryBytes);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTimeSpentInGcMs()).compareTo(other.isSetTimeSpentInGcMs());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTimeSpentInGcMs()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.timeSpentInGcMs, other.timeSpentInGcMs);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetCurrentMemoryBytesUsageByPool()).compareTo(other.isSetCurrentMemoryBytesUsageByPool());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCurrentMemoryBytesUsageByPool()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.currentMemoryBytesUsageByPool, other.currentMemoryBytesUsageByPool);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetMaxMemoryBytes()).compareTo(other.isSetMaxMemoryBytes());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMaxMemoryBytes()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.maxMemoryBytes, other.maxMemoryBytes);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("MemoryStatsRemoteLogEntry(");
    boolean first = true;

    if (isSetTimeFromStartOfCommandMs()) {
      sb.append("timeFromStartOfCommandMs:");
      sb.append(this.timeFromStartOfCommandMs);
      first = false;
    }
    if (isSetFreeMemoryBytes()) {
      if (!first) sb.append(", ");
      sb.append("freeMemoryBytes:");
      sb.append(this.freeMemoryBytes);
      first = false;
    }
    if (isSetTotalMemoryBytes()) {
      if (!first) sb.append(", ");
      sb.append("totalMemoryBytes:");
      sb.append(this.totalMemoryBytes);
      first = false;
    }
    if (isSetTimeSpentInGcMs()) {
      if (!first) sb.append(", ");
      sb.append("timeSpentInGcMs:");
      sb.append(this.timeSpentInGcMs);
      first = false;
    }
    if (isSetCurrentMemoryBytesUsageByPool()) {
      if (!first) sb.append(", ");
      sb.append("currentMemoryBytesUsageByPool:");
      if (this.currentMemoryBytesUsageByPool == null) {
        sb.append("null");
      } else {
        sb.append(this.currentMemoryBytesUsageByPool);
      }
      first = false;
    }
    if (isSetMaxMemoryBytes()) {
      if (!first) sb.append(", ");
      sb.append("maxMemoryBytes:");
      sb.append(this.maxMemoryBytes);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class MemoryStatsRemoteLogEntryStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public MemoryStatsRemoteLogEntryStandardScheme getScheme() {
      return new MemoryStatsRemoteLogEntryStandardScheme();
    }
  }

  private static class MemoryStatsRemoteLogEntryStandardScheme extends org.apache.thrift.scheme.StandardScheme<MemoryStatsRemoteLogEntry> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, MemoryStatsRemoteLogEntry struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TIME_FROM_START_OF_COMMAND_MS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.timeFromStartOfCommandMs = iprot.readI64();
              struct.setTimeFromStartOfCommandMsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // FREE_MEMORY_BYTES
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.freeMemoryBytes = iprot.readI64();
              struct.setFreeMemoryBytesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TOTAL_MEMORY_BYTES
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.totalMemoryBytes = iprot.readI64();
              struct.setTotalMemoryBytesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // TIME_SPENT_IN_GC_MS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.timeSpentInGcMs = iprot.readI64();
              struct.setTimeSpentInGcMsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // CURRENT_MEMORY_BYTES_USAGE_BY_POOL
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map16 = iprot.readMapBegin();
                struct.currentMemoryBytesUsageByPool = new java.util.HashMap<java.lang.String,java.lang.Long>(2*_map16.size);
                java.lang.String _key17;
                long _val18;
                for (int _i19 = 0; _i19 < _map16.size; ++_i19)
                {
                  _key17 = iprot.readString();
                  _val18 = iprot.readI64();
                  struct.currentMemoryBytesUsageByPool.put(_key17, _val18);
                }
                iprot.readMapEnd();
              }
              struct.setCurrentMemoryBytesUsageByPoolIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // MAX_MEMORY_BYTES
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.maxMemoryBytes = iprot.readI64();
              struct.setMaxMemoryBytesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, MemoryStatsRemoteLogEntry struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetTimeFromStartOfCommandMs()) {
        oprot.writeFieldBegin(TIME_FROM_START_OF_COMMAND_MS_FIELD_DESC);
        oprot.writeI64(struct.timeFromStartOfCommandMs);
        oprot.writeFieldEnd();
      }
      if (struct.isSetFreeMemoryBytes()) {
        oprot.writeFieldBegin(FREE_MEMORY_BYTES_FIELD_DESC);
        oprot.writeI64(struct.freeMemoryBytes);
        oprot.writeFieldEnd();
      }
      if (struct.isSetTotalMemoryBytes()) {
        oprot.writeFieldBegin(TOTAL_MEMORY_BYTES_FIELD_DESC);
        oprot.writeI64(struct.totalMemoryBytes);
        oprot.writeFieldEnd();
      }
      if (struct.isSetTimeSpentInGcMs()) {
        oprot.writeFieldBegin(TIME_SPENT_IN_GC_MS_FIELD_DESC);
        oprot.writeI64(struct.timeSpentInGcMs);
        oprot.writeFieldEnd();
      }
      if (struct.currentMemoryBytesUsageByPool != null) {
        if (struct.isSetCurrentMemoryBytesUsageByPool()) {
          oprot.writeFieldBegin(CURRENT_MEMORY_BYTES_USAGE_BY_POOL_FIELD_DESC);
          {
            oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.I64, struct.currentMemoryBytesUsageByPool.size()));
            for (java.util.Map.Entry<java.lang.String, java.lang.Long> _iter20 : struct.currentMemoryBytesUsageByPool.entrySet())
            {
              oprot.writeString(_iter20.getKey());
              oprot.writeI64(_iter20.getValue());
            }
            oprot.writeMapEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetMaxMemoryBytes()) {
        oprot.writeFieldBegin(MAX_MEMORY_BYTES_FIELD_DESC);
        oprot.writeI64(struct.maxMemoryBytes);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class MemoryStatsRemoteLogEntryTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public MemoryStatsRemoteLogEntryTupleScheme getScheme() {
      return new MemoryStatsRemoteLogEntryTupleScheme();
    }
  }

  private static class MemoryStatsRemoteLogEntryTupleScheme extends org.apache.thrift.scheme.TupleScheme<MemoryStatsRemoteLogEntry> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, MemoryStatsRemoteLogEntry struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetTimeFromStartOfCommandMs()) {
        optionals.set(0);
      }
      if (struct.isSetFreeMemoryBytes()) {
        optionals.set(1);
      }
      if (struct.isSetTotalMemoryBytes()) {
        optionals.set(2);
      }
      if (struct.isSetTimeSpentInGcMs()) {
        optionals.set(3);
      }
      if (struct.isSetCurrentMemoryBytesUsageByPool()) {
        optionals.set(4);
      }
      if (struct.isSetMaxMemoryBytes()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetTimeFromStartOfCommandMs()) {
        oprot.writeI64(struct.timeFromStartOfCommandMs);
      }
      if (struct.isSetFreeMemoryBytes()) {
        oprot.writeI64(struct.freeMemoryBytes);
      }
      if (struct.isSetTotalMemoryBytes()) {
        oprot.writeI64(struct.totalMemoryBytes);
      }
      if (struct.isSetTimeSpentInGcMs()) {
        oprot.writeI64(struct.timeSpentInGcMs);
      }
      if (struct.isSetCurrentMemoryBytesUsageByPool()) {
        {
          oprot.writeI32(struct.currentMemoryBytesUsageByPool.size());
          for (java.util.Map.Entry<java.lang.String, java.lang.Long> _iter21 : struct.currentMemoryBytesUsageByPool.entrySet())
          {
            oprot.writeString(_iter21.getKey());
            oprot.writeI64(_iter21.getValue());
          }
        }
      }
      if (struct.isSetMaxMemoryBytes()) {
        oprot.writeI64(struct.maxMemoryBytes);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, MemoryStatsRemoteLogEntry struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.timeFromStartOfCommandMs = iprot.readI64();
        struct.setTimeFromStartOfCommandMsIsSet(true);
      }
      if (incoming.get(1)) {
        struct.freeMemoryBytes = iprot.readI64();
        struct.setFreeMemoryBytesIsSet(true);
      }
      if (incoming.get(2)) {
        struct.totalMemoryBytes = iprot.readI64();
        struct.setTotalMemoryBytesIsSet(true);
      }
      if (incoming.get(3)) {
        struct.timeSpentInGcMs = iprot.readI64();
        struct.setTimeSpentInGcMsIsSet(true);
      }
      if (incoming.get(4)) {
        {
          org.apache.thrift.protocol.TMap _map22 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.I64, iprot.readI32());
          struct.currentMemoryBytesUsageByPool = new java.util.HashMap<java.lang.String,java.lang.Long>(2*_map22.size);
          java.lang.String _key23;
          long _val24;
          for (int _i25 = 0; _i25 < _map22.size; ++_i25)
          {
            _key23 = iprot.readString();
            _val24 = iprot.readI64();
            struct.currentMemoryBytesUsageByPool.put(_key23, _val24);
          }
        }
        struct.setCurrentMemoryBytesUsageByPoolIsSet(true);
      }
      if (incoming.get(5)) {
        struct.maxMemoryBytes = iprot.readI64();
        struct.setMaxMemoryBytesIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

