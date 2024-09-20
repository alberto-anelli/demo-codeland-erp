package it.codeland.support.managementcontrol.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "external_partner")
public class ExternalPartner extends ErpAuditableEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partner", nullable = false)
    private Long id;

    @Column(name = "company_name", nullable = false, length = 50)
    private String companyName;

    @Column(name = "vat", length = 16)
    private String vat;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "sdi", length = 20)
    private String sdi;

    @Column(name = "email")
    private String email;

    @Column(name = "phone", length = 30)
    private String phone;

    @Column(name = "reference_name", nullable = false, length = 50)
    private String referenceName;

    @Column(name = "reference_surname", nullable = false, length = 50)
    private String referenceSurname;

    @Column(name = "reference_phone", length = 30)
    private String referencePhone;

    @Column(name = "reference_email")
    private String referenceEmail;

    @Column(name = "main_skills", length = 4000)
    private String mainSkills;

    @Column(name = "start_collaboration_date")
    private LocalDate collaborationStartDate;

    @Column(name = "stop_collaboration_date")
    private LocalDate collaborationEndDate;

    @OneToMany(mappedBy = "externalPartner", fetch = FetchType.LAZY)
    private List<PartnerProject> partnerProjects;

    public static ExternalPartner match(String key) {
        ExternalPartner partner = new ExternalPartner();
        partner.setCompanyName(key);
        partner.setReferenceName(key);
        partner.setReferenceSurname(key);
        partner.setMainSkills(key);
        return partner;
    }
}
