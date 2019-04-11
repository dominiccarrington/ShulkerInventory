package com.ragegamingpe.shulkerinvent.common.network.message;

import com.ragegamingpe.core.common.network.Message;
import com.ragegamingpe.shulkerinvent.common.ShulkerInvent;
import com.ragegamingpe.shulkerinvent.common.network.GuiHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SlotMessage extends Message
{
    private final Block[] SHULKER_BOXES = new Block[]{
            Blocks.WHITE_SHULKER_BOX,
            Blocks.ORANGE_SHULKER_BOX,
            Blocks.MAGENTA_SHULKER_BOX,
            Blocks.LIGHT_BLUE_SHULKER_BOX,
            Blocks.YELLOW_SHULKER_BOX,
            Blocks.LIME_SHULKER_BOX,
            Blocks.PINK_SHULKER_BOX,
            Blocks.GRAY_SHULKER_BOX,
            Blocks.SILVER_SHULKER_BOX,
            Blocks.CYAN_SHULKER_BOX,
            Blocks.PURPLE_SHULKER_BOX,
            Blocks.BLUE_SHULKER_BOX,
            Blocks.BROWN_SHULKER_BOX,
            Blocks.GREEN_SHULKER_BOX,
            Blocks.RED_SHULKER_BOX,
            Blocks.BLACK_SHULKER_BOX
    };

    public int slot;

    public SlotMessage()
    {

    }

    public SlotMessage(int slot)
    {
        this.slot = slot;
    }

    @Override
    public void handleMessage(MessageContext context)
    {
        EntityPlayerMP player = context.getServerHandler().player;
        Slot slot = player.openContainer.getSlot(this.slot);
        ItemStack stack = null;

        for (Block shulkerbox : SHULKER_BOXES) {
            if (slot.getStack().getItem() == Item.getItemFromBlock(shulkerbox)) {
                stack = slot.getStack();
                break;
            }
        }

        if (stack != null) {
            player.openGui(ShulkerInvent.instance, GuiHandler.SHULKER_BOX, player.world, this.slot, 0, 0);
        }
    }
}
