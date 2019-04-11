/**
 * Autogenerated by Thrift Compiler (0.12.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package no.ntnu.ihb.fmuproxy.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.12.0)", date = "2019-04-11")
public class AvailableFmu implements org.apache.thrift.TBase<AvailableFmu, AvailableFmu._Fields>, java.io.Serializable, Cloneable, Comparable<AvailableFmu> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("AvailableFmu");

  private static final org.apache.thrift.protocol.TField FMU_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("fmuId", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField DEFAULT_EXPERIMENT_FIELD_DESC = new org.apache.thrift.protocol.TField("default_experiment", org.apache.thrift.protocol.TType.STRUCT, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new AvailableFmuStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new AvailableFmuTupleSchemeFactory();

  private @org.apache.thrift.annotation.Nullable java.lang.String fmuId; // required
  private @org.apache.thrift.annotation.Nullable DefaultExperiment default_experiment; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    FMU_ID((short)1, "fmuId"),
    DEFAULT_EXPERIMENT((short)2, "default_experiment");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // FMU_ID
          return FMU_ID;
        case 2: // DEFAULT_EXPERIMENT
          return DEFAULT_EXPERIMENT;
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
    @org.apache.thrift.annotation.Nullable
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
  private static final _Fields optionals[] = {_Fields.DEFAULT_EXPERIMENT};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.FMU_ID, new org.apache.thrift.meta_data.FieldMetaData("fmuId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , "FmuId")));
    tmpMap.put(_Fields.DEFAULT_EXPERIMENT, new org.apache.thrift.meta_data.FieldMetaData("default_experiment", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, DefaultExperiment.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(AvailableFmu.class, metaDataMap);
  }

  public AvailableFmu() {
  }

  public AvailableFmu(
    java.lang.String fmuId)
  {
    this();
    this.fmuId = fmuId;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public AvailableFmu(AvailableFmu other) {
    if (other.isSetFmuId()) {
      this.fmuId = other.fmuId;
    }
    if (other.isSetDefaultExperiment()) {
      this.default_experiment = new DefaultExperiment(other.default_experiment);
    }
  }

  public AvailableFmu deepCopy() {
    return new AvailableFmu(this);
  }

  @Override
  public void clear() {
    this.fmuId = null;
    this.default_experiment = null;
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getFmuId() {
    return this.fmuId;
  }

  public AvailableFmu setFmuId(@org.apache.thrift.annotation.Nullable java.lang.String fmuId) {
    this.fmuId = fmuId;
    return this;
  }

  public void unsetFmuId() {
    this.fmuId = null;
  }

  /** Returns true if field fmuId is set (has been assigned a value) and false otherwise */
  public boolean isSetFmuId() {
    return this.fmuId != null;
  }

  public void setFmuIdIsSet(boolean value) {
    if (!value) {
      this.fmuId = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public DefaultExperiment getDefaultExperiment() {
    return this.default_experiment;
  }

  public AvailableFmu setDefaultExperiment(@org.apache.thrift.annotation.Nullable DefaultExperiment default_experiment) {
    this.default_experiment = default_experiment;
    return this;
  }

  public void unsetDefaultExperiment() {
    this.default_experiment = null;
  }

  /** Returns true if field default_experiment is set (has been assigned a value) and false otherwise */
  public boolean isSetDefaultExperiment() {
    return this.default_experiment != null;
  }

  public void setDefaultExperimentIsSet(boolean value) {
    if (!value) {
      this.default_experiment = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case FMU_ID:
      if (value == null) {
        unsetFmuId();
      } else {
        setFmuId((java.lang.String)value);
      }
      break;

    case DEFAULT_EXPERIMENT:
      if (value == null) {
        unsetDefaultExperiment();
      } else {
        setDefaultExperiment((DefaultExperiment)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case FMU_ID:
      return getFmuId();

    case DEFAULT_EXPERIMENT:
      return getDefaultExperiment();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case FMU_ID:
      return isSetFmuId();
    case DEFAULT_EXPERIMENT:
      return isSetDefaultExperiment();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof AvailableFmu)
      return this.equals((AvailableFmu)that);
    return false;
  }

  public boolean equals(AvailableFmu that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_fmuId = true && this.isSetFmuId();
    boolean that_present_fmuId = true && that.isSetFmuId();
    if (this_present_fmuId || that_present_fmuId) {
      if (!(this_present_fmuId && that_present_fmuId))
        return false;
      if (!this.fmuId.equals(that.fmuId))
        return false;
    }

    boolean this_present_default_experiment = true && this.isSetDefaultExperiment();
    boolean that_present_default_experiment = true && that.isSetDefaultExperiment();
    if (this_present_default_experiment || that_present_default_experiment) {
      if (!(this_present_default_experiment && that_present_default_experiment))
        return false;
      if (!this.default_experiment.equals(that.default_experiment))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetFmuId()) ? 131071 : 524287);
    if (isSetFmuId())
      hashCode = hashCode * 8191 + fmuId.hashCode();

    hashCode = hashCode * 8191 + ((isSetDefaultExperiment()) ? 131071 : 524287);
    if (isSetDefaultExperiment())
      hashCode = hashCode * 8191 + default_experiment.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(AvailableFmu other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetFmuId()).compareTo(other.isSetFmuId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFmuId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.fmuId, other.fmuId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetDefaultExperiment()).compareTo(other.isSetDefaultExperiment());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDefaultExperiment()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.default_experiment, other.default_experiment);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("AvailableFmu(");
    boolean first = true;

    sb.append("fmuId:");
    if (this.fmuId == null) {
      sb.append("null");
    } else {
      sb.append(this.fmuId);
    }
    first = false;
    if (isSetDefaultExperiment()) {
      if (!first) sb.append(", ");
      sb.append("default_experiment:");
      if (this.default_experiment == null) {
        sb.append("null");
      } else {
        sb.append(this.default_experiment);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (default_experiment != null) {
      default_experiment.validate();
    }
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class AvailableFmuStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public AvailableFmuStandardScheme getScheme() {
      return new AvailableFmuStandardScheme();
    }
  }

  private static class AvailableFmuStandardScheme extends org.apache.thrift.scheme.StandardScheme<AvailableFmu> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, AvailableFmu struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // FMU_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.fmuId = iprot.readString();
              struct.setFmuIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // DEFAULT_EXPERIMENT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.default_experiment = new DefaultExperiment();
              struct.default_experiment.read(iprot);
              struct.setDefaultExperimentIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, AvailableFmu struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.fmuId != null) {
        oprot.writeFieldBegin(FMU_ID_FIELD_DESC);
        oprot.writeString(struct.fmuId);
        oprot.writeFieldEnd();
      }
      if (struct.default_experiment != null) {
        if (struct.isSetDefaultExperiment()) {
          oprot.writeFieldBegin(DEFAULT_EXPERIMENT_FIELD_DESC);
          struct.default_experiment.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class AvailableFmuTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public AvailableFmuTupleScheme getScheme() {
      return new AvailableFmuTupleScheme();
    }
  }

  private static class AvailableFmuTupleScheme extends org.apache.thrift.scheme.TupleScheme<AvailableFmu> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, AvailableFmu struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetFmuId()) {
        optionals.set(0);
      }
      if (struct.isSetDefaultExperiment()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetFmuId()) {
        oprot.writeString(struct.fmuId);
      }
      if (struct.isSetDefaultExperiment()) {
        struct.default_experiment.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, AvailableFmu struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.fmuId = iprot.readString();
        struct.setFmuIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.default_experiment = new DefaultExperiment();
        struct.default_experiment.read(iprot);
        struct.setDefaultExperimentIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

