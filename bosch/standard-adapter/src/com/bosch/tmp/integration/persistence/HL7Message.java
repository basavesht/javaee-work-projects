package com.bosch.tmp.integration.persistence;

import com.bosch.tmp.integration.util.HL7MessageProcessingIdEnum;
import com.bosch.tmp.integration.util.AckResponseCodeEnum;
import com.bosch.tmp.integration.util.AckRequestCodeEnum;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.PrePersist;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class is a representation of an HL7 message used for persisting all
 * its properties.
 * Entity annotation is used to create table mapping.
 * Configurable annotation is used to instantiate the HL7Message with Spring injections.
 * Transactional annotation is used for database operations.
 *
 * @author gtk1pal
 */
@Entity
@Configurable
@Transactional
public class HL7Message implements Serializable
{

    private static final long serialVersionUID = 1L;

    public static final Logger logger = LoggerFactory.getLogger(HL7Message.class);

    @PersistenceContext
    transient EntityManager entityManager;

    // Primary key of this HL7 message.
    // Auto-generated.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // This is the ESB's normalized message id (String type).
    // This id is generated by the binding component.
    // Used in conjunction with the esbGroupId to uniquely identify an ESB message.
    // Note: this field has to be populated for each table entry, but cannot be defined
    // as not nullable because entry creation is a two-stage process (due to the use of
    // JAX-WS handler to store raw message).
    private String esbMessageId;

    // This is the ESB's normalized message group id (String type).
    // This id is generated by the binding component.
    // Used in conjunction with the esbMessageId to uniquely identify an ESB message.
    // Note: this field has to be populated for each table entry, but cannot be defined
    // as not nullable because entry creation is a two-stage process (due to the use of
    // JAX-WS handler to store raw message).
    private String esbGroupId;

    // Type of this HL7 message.
    // Example: OML_021, ADT_A04, etc.
    private String type;

    // Structure type of this HL7 message.
    // Example: Structure type of message type ADT_A04 is ADT_A01.
    // This might be needed for future enhancement.
    private String structureType;

    // Control number of this HL7 message.
    // Same control number might be used for retransmission purpose.
    // Type is String since some customers might have letters in the number.
    private String controlNumber;

    // Time when this entry is created, not when the message
    // is created/sent. (UTC format).
    // Auto-generated.
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = true, updatable = false, nullable = false)
    private Date createTimestamp;

    // Time when the message is last created/sent by message sender. (UTC format).
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendTimestamp;

    // The number of times the same message has been sent.
    // Initialized to '1' because the first time a message is persisted is the first time it's sent.
    private int sendCount = 1;

    @Lob
    // Note: this field has to be populated for each table entry, but cannot be defined
    // as not nullable because entry creation is a two-stage process (due to the use of
    // JAX-WS handler to store raw message).
    private char[] rawMessage;

    private String sendingFacilityId;

    private String sendingFacilityDns;

    // Master Patient Index
    private String mpi;

    private String userName;

    // D (debugging), P (production), T (training)
    @Enumerated(EnumType.STRING)
    private HL7MessageProcessingIdEnum processingId;

    private String hl7Version;

    // Whether it’s incoming message or outgoing.
    // Note: this field has to be populated for each table entry, but cannot be defined
    // as not nullable because entry creation is a two-stage process (due to the use of
    // JAX-WS handler to store raw message).
    private Boolean isIncoming;

    // Reference to another HL7Message entry. Only applicable to messages which get a response back.
    // If the response is an ACK, it's the 'application-level' ACK.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESPONSEMESSAGEID")
    private HL7Message responseMessage;

    // Reference to another HL7Message entry, which is the 'message-level' or 'accept-level' ACK.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCEPTACKMESSAGEID")
    private HL7Message acceptAckMessage;

    // Reference to another HL7Message entry, which is another related message.
    // For example, ADT_A04, in addition to its 'message-level' and 'app-level' ACKs,
    // it also needs to be related to QBP_Q22 message.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OTHERRELATEDMESSAGEID")
    private HL7Message otherRelatedMessage;

    // 'ACK' message only. CA, CE, CR, AA, AR, AE.
    @Enumerated(EnumType.STRING)
    private AckResponseCodeEnum ackTypeCode;

    // 'ACK' message only.
    private String ackFaultCode;

    // 'ACK' message only.
    @Column(length = 1000)
    private String ackFaultMessage;

    // Accept ACK request code, default to Never.
    // AL (Always), NE (Never), SU (Success), ER (Error).
    @Enumerated(EnumType.STRING)
    private AckRequestCodeEnum acceptAckRequestCode = AckRequestCodeEnum.NE;

    // App ACK request code, default to Never.
    // AL (Always), NE (Never), SU (Success), ER (Error).
    @Enumerated(EnumType.STRING)
    private AckRequestCodeEnum appAckRequestCode = AckRequestCodeEnum.NE;

    // To automatically generate current timestamp on creation of an entry.
    @PrePersist
    protected void onCreate()
    {
        createTimestamp = new Date();
    }

    public String getAckFaultCode()
    {
        return ackFaultCode;
    }

    public void setAckFaultCode(String ackFaultCode)
    {
        this.ackFaultCode = ackFaultCode;
    }

    public String getAckFaultMessage()
    {
        return ackFaultMessage;
    }

    public void setAckFaultMessage(String ackFaultMessage)
    {
        this.ackFaultMessage = ackFaultMessage;
    }

    public AckResponseCodeEnum getAckTypeCode()
    {
        return ackTypeCode;
    }

    public void setAckTypeCode(AckResponseCodeEnum ackTypeCode)
    {
        this.ackTypeCode = ackTypeCode;
    }

    public AckRequestCodeEnum getAcceptAckRequestCode()
    {
        return acceptAckRequestCode;
    }

    public void setAcceptAckRequestCode(AckRequestCodeEnum acceptAckRequestCode)
    {
        this.acceptAckRequestCode = acceptAckRequestCode;
    }

    public AckRequestCodeEnum getAppAckRequestCode()
    {
        return appAckRequestCode;
    }

    public void setAppAckRequestCode(AckRequestCodeEnum appAckRequestCode)
    {
        this.appAckRequestCode = appAckRequestCode;
    }

    public String getControlNumber()
    {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber)
    {
        this.controlNumber = controlNumber;
    }

    public Date getCreateTimestamp()
    {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp)
    {
        this.createTimestamp = createTimestamp;
    }

    public String getEsbGroupId()
    {
        return esbGroupId;
    }

    public void setEsbGroupId(String esbGroupId)
    {
        this.esbGroupId = esbGroupId;
    }

    public String getEsbMessageId()
    {
        return esbMessageId;
    }

    public void setEsbMessageId(String esbMessageId)
    {
        this.esbMessageId = esbMessageId;
    }

    public String getHl7Version()
    {
        return hl7Version;
    }

    public void setHl7Version(String hl7Version)
    {
        this.hl7Version = hl7Version;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Boolean getIsIncoming()
    {
        return isIncoming;
    }

    public void setIsIncoming(Boolean isIncoming)
    {
        this.isIncoming = isIncoming;
    }

    public String getMpi()
    {
        return mpi;
    }

    public void setMpi(String mpi)
    {
        this.mpi = mpi;
    }

    public HL7MessageProcessingIdEnum getProcessingId()
    {
        return processingId;
    }

    public void setProcessingId(HL7MessageProcessingIdEnum processingId)
    {
        this.processingId = processingId;
    }

    public char[] getRawMessage()
    {
        return rawMessage;
    }

    public void setRawMessage(char[] rawMessage)
    {
        this.rawMessage = rawMessage;
    }

    public HL7Message getResponseMessage()
    {
        return responseMessage;
    }

    public void setResponseMessage(HL7Message responseMessage)
    {
        this.responseMessage = responseMessage;
    }

    public HL7Message getAcceptAckMessage()
    {
        return acceptAckMessage;
    }

    public void setAcceptAckMessage(HL7Message acceptAckMessage)
    {
        this.acceptAckMessage = acceptAckMessage;
    }

    public HL7Message getOtherRelatedMessage()
    {
        return otherRelatedMessage;
    }

    public void setOtherRelatedMessage(HL7Message otherRelatedMessage)
    {
        this.otherRelatedMessage = otherRelatedMessage;
    }

    public String getSendingFacilityDns()
    {
        return sendingFacilityDns;
    }

    public void setSendingFacilityDns(String sendingFacilityDns)
    {
        this.sendingFacilityDns = sendingFacilityDns;
    }

    public String getSendingFacilityId()
    {
        return sendingFacilityId;
    }

    public void setSendingFacilityId(String sendingFacilityId)
    {
        this.sendingFacilityId = sendingFacilityId;
    }

    public Date getSendTimestamp()
    {
        return sendTimestamp;
    }

    public void setSendTimestamp(Date sendTimestamp)
    {
        this.sendTimestamp = sendTimestamp;
    }

    public int getSendCount()
    {
        return sendCount;
    }

    public void setSendCount(int sendCount)
    {
        this.sendCount = sendCount;
    }

    public String getStructureType()
    {
        return structureType;
    }

    public void setStructureType(String structureType)
    {
        this.structureType = structureType;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HL7Message))
        {
            return false;
        }
        HL7Message other = (HL7Message) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.bosch.tmp.integration.persistence.HL7Message[id=" + id + "]";
    }

    @Transactional
    public void persist()
    {
        if (this.entityManager == null)
        {
            this.entityManager = entityManager();
        }
        this.entityManager.persist(this);
    }

    @Transactional
    public void remove()
    {
        if (this.entityManager == null)
        {
            this.entityManager = entityManager();
        }
        if (this.entityManager.contains(this))
        {
            this.entityManager.remove(this);
        }
        else
        {
            HL7Message attached = this.entityManager.find(HL7Message.class, this.id);
            this.entityManager.remove(attached);
        }
    }

    @Transactional
    public void flush()
    {
        if (this.entityManager == null)
        {
            this.entityManager = entityManager();
        }
        this.entityManager.flush();
    }

    @Transactional
    public void merge()
    {
        if (this.entityManager == null)
        {
            this.entityManager = entityManager();
        }
        HL7Message merged = this.entityManager.merge(this);
        this.entityManager.flush();
        this.id = merged.getId();
    }

    public static final EntityManager entityManager()
    {
        EntityManager em = new HL7Message().entityManager;
        if (em == null)
        {
            throw new IllegalStateException(
                    "Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        }
        return em;
    }

    public static long countHL7Messages()
    {
        return (Long) entityManager().createQuery("select count(o) from HL7Message o").getSingleResult();
    }

    public static List<HL7Message> findAllHL7Messages()
    {
        return entityManager().createQuery("select o from HL7Message o").getResultList();
    }

    public static HL7Message findHL7Message(Long id)
    {
        if (id == null)
        {
            return null;
        }
        return entityManager().find(HL7Message.class, id);
    }

    public static List<HL7Message> findHL7MessageEntries(int firstResult, int maxResults)
    {
        return entityManager().createQuery("select o from HL7Message o").setFirstResult(firstResult).setMaxResults(
                maxResults).getResultList();
    }

    public static List<HL7Message> findHL7MessagesByType(String type)
    {
        if (type == null)
        {
            throw new IllegalArgumentException("The type argument is required");
        }
        EntityManager em = HL7Message.entityManager();
        Query q = em.createQuery("select HL7Message from HL7Message as hl7Message where hl7Message.type = :type");
        q.setParameter("type", type);
        return q.getResultList();
    }

    public static HL7Message findHL7MessageByEsbIdsAndDirection(String esbGroupId, String esbMessageId,
            Boolean isIncoming)
    {
        if (esbMessageId == null)
        {
            throw new IllegalArgumentException("The esbMessageId argument is required");
        }
        EntityManager em = HL7Message.entityManager();
        Query q = em.createQuery("select HL7Message from HL7Message as hl7Message where " +
                "hl7Message.esbGroupId " + ((esbGroupId == null) ? "is null and " : "= :esbGroupId and ") +
                "hl7Message.esbMessageId = :esbMessageId and " +
                "hl7Message.isIncoming = :isIncoming " +
                "order by hl7Message.id desc");
        if (esbGroupId != null)
        {
            q.setParameter("esbGroupId", esbGroupId);
        }
        q.setParameter("esbMessageId", esbMessageId);
        q.setParameter("isIncoming", isIncoming);
        List<HL7Message> list = q.getResultList();
        if (list == null || list.size() == 0)
        {
            return null;
        }
        else
        {
            return list.get(0);
        }
    }

    public static HL7Message findHL7MessageByControlNumber(String controlNumber)
    {
        if (controlNumber == null)
        {
            throw new IllegalArgumentException("The controlNumber argument is required");
        }
        EntityManager em = HL7Message.entityManager();
        Query q = em.createQuery("select HL7Message from HL7Message as hl7Message where " +
                "hl7Message.controlNumber = :controlNumber and " +
                "hl7Message.id = (select max(hl7Message2.id) from HL7Message as hl7Message2 where " +
                "hl7Message2.controlNumber = hl7Message.controlNumber)");
        q.setParameter("controlNumber", controlNumber);
        List<HL7Message> list = q.getResultList();
        if (list == null || list.size() == 0)
        {
            return null;
        }
        else
        {
            return list.get(0);
        }
    }

    public static HL7Message findHL7MessageByNullRawMessageAndDirection(Boolean isIncoming)
    {
        EntityManager em = HL7Message.entityManager();
        Query q = em.createQuery("select HL7Message from HL7Message as hl7Message where " +
                "hl7Message.rawMessage is null and " +
                "hl7Message.isIncoming = :isIncoming " +
                "order by hl7Message.id desc");
        q.setParameter("isIncoming", isIncoming);
        List<HL7Message> list = q.getResultList();
        if (list == null || list.size() == 0)
        {
            return null;
        }
        else
        {
            return list.get(0);
        }
    }

    public static HL7Message findHL7MessageByResponseMessage(HL7Message responseMessage)
    {
        EntityManager em = HL7Message.entityManager();
        Query q = em.createQuery("select HL7Message from HL7Message as hl7Message where " +
                "hl7Message.responseMessage " + ((responseMessage == null) ? "is null" : "= :responseMessage"));
        q.setParameter("responseMessage", responseMessage);
        List<HL7Message> list = q.getResultList();
        if (list == null || list.size() == 0)
        {
            return null;
        }
        else
        {
            return list.get(0);
        }
    }

    public static List<HL7Message> findUnacknowledgedOutgoingHL7Messages()
    {
        EntityManager em = HL7Message.entityManager();
        Query q = em.createQuery("select HL7Message from HL7Message as hl7Message " +
                "where hl7Message.isIncoming = :isIncoming and " +
                "(hl7Message.responseMessage is null and hl7Message.appAckRequestCode = :appAckRequestCode))");
        q.setParameter("isIncoming", Boolean.FALSE);
        q.setParameter("appAckRequestCode", AckRequestCodeEnum.AL);
        return q.getResultList();
    }

    /**
     * Find the app-ack response of the first existing incoming HL7Message which has the same
     * control number and sending facility ID as the inputs.
     * ASSUMPTION: The last persisted incoming HL7Message with the control number and sending facility ID should be
     * ignored.
     *
     * @param controlNumber The control number of the HL7Message to find.
     * @param sendingFacilityId The sending facility ID of the HL7Message to find.
     * @param isAcceptAck The flag whether response is accept-ack or app-ack.
     * @return The HL7Message found.
     */
    public static HL7Message findDuplicateHL7MessageResponse(String controlNumber, String sendingFacilityId)
    {
        if (controlNumber == null)
        {
            throw new IllegalArgumentException("The controlNumber argument is required");
        }
        EntityManager em = HL7Message.entityManager();
        Query q = em.createQuery("select hl7Message.responseMessage" +
                " from HL7Message as hl7Message where " +
                "hl7Message.isIncoming = :isIncoming and " +
                "hl7Message.controlNumber = :controlNumber and " +
                "hl7Message.sendingFacilityId " + ((sendingFacilityId == null) ? "is null " : "= :sendingFacilityId ") +
                "order by hl7Message.id asc");
        q.setParameter("isIncoming", Boolean.TRUE);
        q.setParameter("controlNumber", controlNumber);
        q.setParameter("sendingFacilityId", sendingFacilityId);

        HL7Message msg = null;
        List<HL7Message> ret = q.getResultList();
        // The last entry should be ignored.
        if (ret != null && ret.size() > 0)
        {
            msg = ret.get(0);
        }
        return msg;
    }
}
