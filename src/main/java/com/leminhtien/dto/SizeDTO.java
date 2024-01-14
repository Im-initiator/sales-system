package com.leminhtien.dto;
import java.util.Objects;

public class SizeDTO extends BaseDTO{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}

		if (obj.getClass()!=this.getClass()){
			return false;
		}

		if (this==obj){
			return true;
		}

		SizeDTO size = (SizeDTO) obj;
		return this.getId().equals(size.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId());
	}

}
