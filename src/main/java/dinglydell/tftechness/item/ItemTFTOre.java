package dinglydell.tftechness.item;

import net.minecraft.item.ItemStack;

import com.bioxx.tfc.Items.ItemOre;
import com.bioxx.tfc.api.Metal;

public class ItemTFTOre extends ItemOre {
	Metal[] metals;
	short[] amounts;
	EnumTier[] tiers;

	public ItemTFTOre(String[] names, Metal[] metals, short[] amounts,
			EnumTier[] tiers) {
		super();
		metaNames = names;
		this.metals = metals;
		this.amounts = amounts;
		this.tiers = tiers;
	}

	@Override
	public Metal getMetalType(ItemStack is) {
		return metals[is.getItemDamage()];
	}

	@Override
	public short getMetalReturnAmount(ItemStack is) {
		return this.amounts[is.getItemDamage()];
	}

	@Override
	public boolean isSmeltable(ItemStack is) {

		return this.metals[is.getItemDamage()] != null;
	}

	@Override
	public EnumTier getSmeltTier(ItemStack is) {
		return tiers[is.getItemDamage()];
	}

}