package app.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Agenda { // A FAZER (sem lógica ainda)

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long idAgendamento;
	private LocalDateTime dataAgendamento;
	
	//DataTimeFormatter formatter = DataTimeFormatter.offPatter
}
