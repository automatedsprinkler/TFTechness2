package dinglydell.tftechness.metal;

import com.bioxx.tfc.api.HeatRaw;

public class MetalStat {
	public HeatRaw heat;
	/** Density in kg/m^3 */
	public int density;

	/**
	 * @param specificHeat
	 *            Specific heat in kj/(kg K)
	 * @param meltTemp
	 *            Melting point in degrees C
	 * @param density
	 *            Density in kg/m^3
	 * */
	public MetalStat(double specificHeat, double meltTemp, int density) {
		this.heat = new HeatRaw(specificHeat, meltTemp);
		this.density = density;
	}

	/**
	 * Returns the specific heat in SI units (j/(kg K))
	 * 
	 * TFC uses kj/(kg K) by default
	 * */
	public float getSISpecificHeat() {
		return heat.specificHeat / 1000;
	}
}
