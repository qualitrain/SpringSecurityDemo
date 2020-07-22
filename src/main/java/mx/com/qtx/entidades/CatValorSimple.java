package mx.com.qtx.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CatValorSimple {
	@Id
	@Column(name="cat_id")
	private long idValorSimple;
	@Column(name="cat_tipo_valor")
	private String tipoValor; // Por ejemplo, "nombre","apellido","genero", "cp"
	@Column(name="cat_valor_alfa")
	private String valorAlfa; // Por ejemplo, "Pedro", "Carlos", etc. para un tipoValor == "nombre"
	@Column(name="cat_valor_corto_alfa")
	private String valorCortoAlfa; //

	public CatValorSimple() {
		super();
	}

	public CatValorSimple(long idValorSimple, String tipoValor, String valorAlfa) {
		super();
		this.idValorSimple = idValorSimple;
		this.tipoValor = tipoValor;
		this.valorAlfa = valorAlfa;
	}

	public long getIdValorSimple() {
		return idValorSimple;
	}

	public void setIdValorSimple(long idValorSimple) {
		this.idValorSimple = idValorSimple;
	}

	public String getTipoValor() {
		return tipoValor;
	}

	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}

	public String getValorAlfa() {
		return valorAlfa;
	}

	public void setValorAlfa(String valorAlfa) {
		this.valorAlfa = valorAlfa;
	}

	public String getValorCortoAlfa() {
		return valorCortoAlfa;
	}

	public void setValorCortoAlfa(String valorCortoAlfa) {
		this.valorCortoAlfa = valorCortoAlfa;
	}

	@Override
	public String toString() {
		return "CatValorSimple [idValorSimple=" + idValorSimple + ", tipoValor=" + tipoValor + ", valorAlfa="
				+ valorAlfa + ", valorCortoAlfa=" + valorCortoAlfa + "]";
	}


}
