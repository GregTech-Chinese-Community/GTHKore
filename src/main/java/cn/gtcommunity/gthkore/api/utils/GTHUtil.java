package cn.gtcommunity.gthkore.api.utils;

import cn.gtcommunity.gthkore.Tags;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.util.BlockInfo;
import gregtech.api.util.TextFormattingUtil;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class GTHUtil {

    public static ResourceLocation asId(String path) {
        return new ResourceLocation(Tags.MOD_ID, path);
    }

    /**
     * @param number Long number.
     * @return Just a rewrite of formatNumbers().
     */
    public static String formatNumbers(long number) {
        return TextFormattingUtil.formatNumbers(number);
    }

    /**
     * @param number Double number.
     * @return Just a rewrite of formatNumbers().
     */
    public static String formatNumbers(double number) {
        return TextFormattingUtil.formatNumbers(number);
    }

    /**
     * @param tag NBT tag.
     * @param key Key.
     * @param defaultValue Default value.
     * @return If tag has special key, then return integer of key (value), if not, then return default value.
     */
    public static int getOrDefault(NBTTagCompound tag,
                                   String key,
                                   int defaultValue){
        if (tag.hasKey(key)) {
            return tag.getInteger(key);
        }
        return defaultValue;
    }

    /**
     * @return Used to check if multiblock part has tier and set its related info.
     */
    public static <T> T getOrDefault(BooleanSupplier canGet,
                                     Supplier<T> getter,
                                     T defaultValue){
        return canGet.getAsBoolean() ? getter.get() : defaultValue;
    }

    /**
     * @param lists List.
     * @return List size, used to get ArrayList<List<IBlockState>> in some block tier multiblocks.
     */
    public static <T> int maxLength(List<List<T>> lists) {
        return lists.stream()
                .mapToInt(List::size)
                .max()
                .orElse(0);
    }

    /**
     * @param list List.
     * @param length List size.
     * @return The final list, used to recheck list of IBlockState in some block tier multiblocks.
     */
    public static <T> List<T> consistentList(List<T> list,
                                             int length) {
        if (list.size() >= length) {
            return list;
        }
        List<T> finalList = new ArrayList<>(list);
        T last = list.get(list.size() - 1);
        for (int i = 0; i < length - list.size(); i++) {
            finalList.add(last);
        }
        return finalList;
    }

    /**
     * @param tile MetaTileEntity.
     * @return MetaTileEntityHolder, used to get special block info in Traceability Predicate.
     */
    public static MetaTileEntityHolder getTileEntity(MetaTileEntity tile) {
        MetaTileEntityHolder holder = new MetaTileEntityHolder();
        holder.setMetaTileEntity(tile);
        holder.getMetaTileEntity().onPlacement();
        holder.getMetaTileEntity().setFrontFacing(EnumFacing.SOUTH);
        return holder;
    }

    /**
     * @param allowedStates Allowed Block States.
     * @return Used to build upgrade multiblock.
     */
    public static Supplier<BlockInfo[]> getCandidates(IBlockState... allowedStates) {
        return () -> Arrays.stream(allowedStates)
                .map(state -> new BlockInfo(state, null))
                .toArray(BlockInfo[]::new);
    }

    /**
     * @param metaTileEntities Allowed Meta Tile Entities.
     * @return Used to build upgrade multiblock.
     */
    public static Supplier<BlockInfo[]> getCandidates(MetaTileEntity... metaTileEntities) {
        return () -> Arrays.stream(metaTileEntities)
                .filter(Objects::nonNull)
                .map(tile -> new BlockInfo(MetaBlocks.MACHINE.getDefaultState(), getTileEntity(tile)))
                .toArray(BlockInfo[]::new);
    }

    /**
     * @param value Int value.
     * @param min Min value.
     * @param max Max value.
     * @return If value < min value, then return min, if value >= min, then return min(value, max).
     */
    public static int clamp(int value, int min, int max) {
        if (value < min) {
            return min;
        } else {
            return Math.min(value, max);
        }
    }

    /**
     * @param value Double value.
     * @param min Min value.
     * @param max Max value.
     * @return If value < min value, then return min, if value >= min, then return min(value, max).
     */
    public static double clamp(double value, double min, double max) {
        if (value < min) {
            return min;
        }else {
            return Math.min(value, max);
        }
    }

    /**
     * @param values long value.
     * @return summarized values.
     */
    public static BigInteger summarizedValue(long[] values) {
        BigInteger retValue = BigInteger.ZERO;
        long currentSum = 0;

        for (long value : values) {
            if (currentSum != 0 && value > Long.MAX_VALUE - currentSum) {
                retValue = retValue.add(BigInteger.valueOf(currentSum));
                currentSum = 0;
            }
            currentSum += value;
        }

        if (currentSum != 0) {
            retValue = retValue.add(BigInteger.valueOf(currentSum));
        }

        return retValue;
    }


}
