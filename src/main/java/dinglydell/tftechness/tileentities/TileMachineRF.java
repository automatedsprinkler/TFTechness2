package dinglydell.tftechness.tileentities;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import blusunrize.immersiveengineering.common.IEContent;

public abstract class TileMachineRF extends TileMachineComponent {
	public enum WireTier {
		lv(256, 1f), mv(1024, 0.85f), hv(4096, 0.7f);

		public final float efficiency;
		public final int transferRate;

		WireTier(int transferRate, float efficiency) {
			this.transferRate = transferRate;
			this.efficiency = efficiency;
		}

		public ItemStack getWire() {
			return new ItemStack(IEContent.itemWireCoil, 1, this.ordinal());
		}
	}

	protected WireTier tier = WireTier.lv;

	private int rfRate;

	public TileMachineRF setWireTier(WireTier tier) {
		this.tier = tier;
		//default consuming machine capacity is 20 seconds of power
		this.setRFCapacity(tier.transferRate * 400);
		return this;
	}

	@Override
	public void writeServerToClientMessage(NBTTagCompound nbt) {

		super.writeServerToClientMessage(nbt);
		nbt.setInteger("tier", tier.ordinal());
		nbt.setInteger("rfRate", rfRate);

	}

	@Override
	public void readServerToClientMessage(NBTTagCompound nbt) {

		super.readServerToClientMessage(nbt);
		tier = WireTier.values()[nbt.getInteger("tier")];
		rfRate = nbt.getInteger("rfRate");
	}

	@Override
	public void updateEntity() {

		super.updateEntity();
		if (!worldObj.isRemote) {

			//redstone power -> shutdown
			if (worldObj.getBlockPowerInput(xCoord, yCoord, zCoord) != 0) {
				rfRate = 0;
			} else {
				rfRate = spendRF(Math.min(rf, tier.transferRate));
				rf -= rfRate;
			}

		}
	}

	protected abstract int spendRF(int amt);

	public int getEnergyConsumptionRate() {
		return rfRate;
	}

	public WireTier getWireTier() {
		return this.tier;
	}
}
