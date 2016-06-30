package com.kanomiya.mcmod.mirufurniture.block;

import com.kanomiya.mcmod.mirufurniture.MiruFurniture;
import com.kanomiya.mcmod.mirufurniture.tileentity.TileEntityStorageShelf;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStorageShelf extends BlockContainer {
	public static final PropertyDirection FACING = PropertyDirection.create(MFBlockConsts.NAME_FACING, EnumFacing.Plane.HORIZONTAL);

	public BlockStorageShelf() {
		super(Material.WOOD);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));

		setHardness(1.5F);
		setSoundType(SoundType.WOOD);
		setCreativeTab(MiruFurniture.tabMF);
        setUnlocalizedName("blockStorageShelf");

	}

	@Override public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) { return false; }

		playerIn.openGui(MiruFurniture.instance, MiruFurniture.GUIID_STORAGESHELF, worldIn, pos.getX(), pos.getY(), pos.getZ());

		return true;

	}



	@Override public float getEnchantPowerBonus(World world, BlockPos pos) {
		TileEntityStorageShelf te = (TileEntityStorageShelf) world.getTileEntity(pos);
		if (te == null) { return 0; }

		return te.getEnchantPowerBonus();
	}




	@Override public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return getStateFromMeta(meta).withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof IInventory) {
			InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileentity);
			worldIn.updateComparatorOutputLevel(pos, this);
		}

		super.breakBlock(worldIn, pos, state);
	}









	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}

	@Override @SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}




	@Override public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing enumfacing = EnumFacing.getFront(meta);

		if (enumfacing.getAxis() == EnumFacing.Axis.Y)
		{
			enumfacing = EnumFacing.NORTH;
		}

		return getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override public int getMetaFromState(IBlockState state)
	{
		return state.getValue(FACING).getHorizontalIndex();
	}


	@Override protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { FACING }); //, HAS_BOOK[0], HAS_BOOK[1], HAS_BOOK[2]});
	}

	@Override public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityStorageShelf();
	}

	@Override public boolean isOpaqueCube(IBlockState state) { return true; }

	@Override public boolean isFullCube(IBlockState state) { return false; }

	@Override public boolean isFullBlock(IBlockState state) { return false; }

	@Override public boolean getUseNeighborBrightness(IBlockState state) { return true; }


}
