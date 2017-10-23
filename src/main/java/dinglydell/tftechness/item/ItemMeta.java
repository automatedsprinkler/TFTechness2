package dinglydell.tftechness.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMeta {
	public Item item;
	public int meta;

	public ItemMeta(Item item, int meta) {
		this.item = item;
		this.meta = meta;
	}

	public ItemMeta(ItemStack stack) {
		this(stack.getItem(), stack.getItemDamage());
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ItemMeta)) {
			return false;
		}
		ItemMeta im = (ItemMeta) obj;
		return im.item == this.item && im.meta == this.meta;
	}

	@Override
	public int hashCode() {

		return 16 * item.hashCode() + meta;
	}

}