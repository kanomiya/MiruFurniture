package com.kanomiya.mcmod.mirufurniture.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.kanomiya.mcmod.mirufurniture.MiruFurniture;
import com.kanomiya.mcmod.mirufurniture.tileentity.TileEntityStorageShelf;

public class BlockStorageShelf extends BlockContainer {
	public static final PropertyDirection FACING = PropertyDirection.create(MFBlockConsts.NAME_FACING, EnumFacing.Plane.HORIZONTAL);

	public BlockStorageShelf() {
		super(Material.wood);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));

		setHardness(1.5F);
		setStepSound(soundTypeWood);
		setCreativeTab(MiruFurniture.tabMF);

	}

	@Override public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (world.isRemote) { return false; }

		player.openGui(MiruFurniture.instance, MiruFurniture.GUIID_STORAGESHELF, world, pos.getX(), pos.getY(), pos.getZ());

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








	@Override public int getRenderType() {
		return 3;
	}

	@Override @SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT_MIPPED;
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


	@Override protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { FACING }); //, HAS_BOOK[0], HAS_BOOK[1], HAS_BOOK[2]});
	}

	@Override public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityStorageShelf();
	}

	@Override public boolean isOpaqueCube() { return true; }

	@Override public boolean isFullCube() { return false; }

	@Override public boolean isFullBlock() { return false; }

	@Override public boolean getUseNeighborBrightness() { return true; }


}
