package com.syndicate.master.state;

import java.io.Serializable;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "state_gst")
public class StateGst implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")	
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "GST_code")
    private Integer gstCode;
	
	@Column(name = "GST_alpha_code")
    private String gstAlphaCode;
    
}
