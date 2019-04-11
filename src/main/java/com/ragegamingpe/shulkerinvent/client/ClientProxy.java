package com.ragegamingpe.shulkerinvent.client;

import com.ragegamingpe.shulkerinvent.common.CommonProxy;
import com.ragegamingpe.shulkerinvent.common.network.MessageHandler;
import com.ragegamingpe.shulkerinvent.common.network.message.SlotMessage;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy
{
    private static KeyBinding openShulkerBoxBinding;

    private final Block[] SHULKER_BOXES = new Block[]{Blocks.WHITE_SHULKER_BOX,
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
            Blocks.BLACK_SHULKER_BOX};

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        CommonProxy.registeredEvents = true;
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        ClientRegistry.registerKeyBinding(openShulkerBoxBinding = new KeyBinding("key.shulkerinvent.openshulkerbox", Keyboard.KEY_O, "key.categories.inventory"));
    }

    @SubscribeEvent
    public void onGuiKeyboardEvent(GuiScreenEvent.KeyboardInputEvent.Pre event)
    {
        this.runKeyboard(event);
    }

    public void runKeyboard(GuiScreenEvent.KeyboardInputEvent event)
    {
        if (event.getGui() instanceof GuiContainer) {
            GuiContainer container = (GuiContainer) event.getGui();

            Slot slot = container.getSlotUnderMouse();
            if (slot != null) {
                ItemStack stack = null;

                for (Block shulkerbox : SHULKER_BOXES) {
                    if (slot.getStack().getItem() == Item.getItemFromBlock(shulkerbox)) {
                        stack = slot.getStack();
                        break;
                    }
                }

                if (stack != null && openShulkerBoxBinding.isActiveAndMatches(Keyboard.getEventKey())) {
                    // We got a live one bois...

                    int i = 0;
                    for (Slot s : container.inventorySlots.inventorySlots) {
                        if (s == slot)
                            MessageHandler.INSTANCE.sendToServer(new SlotMessage(i));
                        i++;
                    }
                }
            }
        }
    }
}
