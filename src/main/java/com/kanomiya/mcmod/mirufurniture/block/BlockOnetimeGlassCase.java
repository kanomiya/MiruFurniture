package com.kanomiya.mcmod.mirufurniture.block;

import java.util.List;

import com.kanomiya.mcmod.kanomiyacore.util.bit.BitFieldHelper;
import com.kanomiya.mcmod.kanomiyacore.util.bit.BitFieldStructure;
import com.kanomiya.mcmod.mirufurniture.MiruFurniture;
import com.kanomiya.mcmod.mirufurniture.tileentity.TileEntityOnetimeGlassCase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Kanomiya
 *
 */
public class BlockOnetimeGlassCase extends BlockContainer {
	public static final BitFieldStructure BITFIELD = new BitFieldStructure() {
		{
			// BitData
			// 0b0XXY
			//
			// X: 向き(4)
			// Y: 破損状態(2)

			allocate(MFBlockConsts.NAME_BROKEN, 0, 1); // 0b00Y
			allocate(MFBlockConsts.NAME_FACING, 2, 2); // 0bXX0

		}
	};

	public static final PropertyDirection FACING = PropertyDirection.create(MFBlockConsts.NAME_FACING, EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool BROKEN = PropertyBool.create(MFBlockConsts.NAME_BROKEN);


	public BlockOnetimeGlassCase() {
		super(Material.ROCK);

		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(BROKEN, Boolean.FALSE));
		setHardness(1.25f);
		setResistance(7.0f);
		setCreativeTab(MiruFurniture.tabMF);
		setUnlocalizedName("blockOnetimeGlassCase_cube");
	}

	@Override public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
			TileEntity tileEntity = worldIn.getTileEntity(pos);

		if (tileEntity instanceof TileEntityOnetimeGlassCase && heldItem != null) {
			TileEntityOnetimeGlassCase tileCase = (TileEntityOnetimeGlassCase) tileEntity;

			boolean successFlag = false;
			boolean consumedItem = false;

			if (! tileCase.hasDisplayedItem()) {
				ItemStack displayStack = heldItem.copy();
				displayStack.stackSize = 1;
				tileCase.setDisplayedItem(displayStack);

				consumedItem = (playerIn.inventory.getCurrentItem().stackSize -- <= 0);
				successFlag = true;

			} else if (state.getValue(BROKEN))  {
				if (heldItem.getItem() == Item.getItemFromBlock(Blocks.GLASS_PANE)) {

					NBTTagCompound tag = new NBTTagCompound();
					tileEntity.writeToNBT(tag);

					worldIn.setBlockState(pos, state.withProperty(BROKEN, Boolean.FALSE));
					tileEntity = worldIn.getTileEntity(pos);

					if (tileEntity != null) tileEntity.readFromNBT(tag);

					consumedItem = (playerIn.inventory.getCurrentItem().stackSize -- <= 0);
					successFlag = true;
				}

			}

			if (consumedItem) {
				playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, null);
			}

			return successFlag;
		}

		return false;
	}

	@Override public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, playerIn.getHeldItemMainhand()) > 0) return;

		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileEntity = worldIn.getTileEntity(pos);

		EnumFacing facing = state.getValue(FACING);
		BlockPos pos2 = pos.offset(facing);


		if (tileEntity instanceof IInventory) {
			if (! worldIn.isRemote){
				InventoryHelper.dropInventoryItems(worldIn, pos2, (IInventory) tileEntity);
			}

			((IInventory) tileEntity).clear();

			worldIn.updateComparatorOutputLevel(pos, this);

		}

		if (! (Boolean) state.getValue(BROKEN)) { // BlockGlass
			if (tileEntity instanceof TileEntityOnetimeGlassCase) {
				worldIn.playSound(pos.getX() +0.5d, pos.getY() +0.5d, pos.getZ() +0.5d, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f, false);


				int x = (pos.getX() + pos2.getX()) /2;
				int y = (pos.getY() + pos2.getY()) /2;
				int z = (pos.getZ() + pos2.getZ()) /2;
				int[] ix = new int[] { Block.getStateId(Blocks.GLASS_PANE.getDefaultState()) };

				worldIn.spawnParticle(EnumParticleTypes.BLOCK_CRACK, x, y, z, 3.5d, 3.5d, 3.5d, ix);
				worldIn.spawnParticle(EnumParticleTypes.BLOCK_CRACK, x, y, z, 3.5d, 3.5d, 3.5d, ix);
				worldIn.spawnParticle(EnumParticleTypes.BLOCK_CRACK, x, y, z, 3.5d, 3.5d, 3.5d, ix);

				NBTTagCompound tag = new NBTTagCompound();
				tileEntity.writeToNBT(tag);

				worldIn.setBlockState(pos, state.withProperty(BROKEN, Boolean.TRUE));
				tileEntity = worldIn.getTileEntity(pos);
			}

		}

	}


	@Override public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (! worldIn.isRemote) {
			TileEntity tileentity = worldIn.getTileEntity(pos);

			if (tileentity instanceof IInventory) {
				InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
				worldIn.updateComparatorOutputLevel(pos, this);
			}

		}

		super.breakBlock(worldIn, pos, state);
	}







	@Override  public boolean hasComparatorInputOverride(IBlockState state)
	{
		return true;
	}

	@Override public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
	{
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}








	/**
	 * @inheritDoc
	 */
	@Override public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityOnetimeGlassCase();
	}



	@Override public int damageDropped(IBlockState state)  {
		return (getMetaFromState(state) & 1); // 0b1
	}

	@Override @SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		list.add(new ItemStack(itemIn, 1, 0));
		list.add(new ItemStack(itemIn, 1, 1));
	}

	@Override public IBlockState getStateFromMeta(int meta)
	{
		IBlockState state = getDefaultState();

		EnumFacing enumfacing = EnumFacing.getFront(BITFIELD.getValue(MFBlockConsts.NAME_FACING, meta));

		if (enumfacing.getAxis() == EnumFacing.Axis.Y) enumfacing = EnumFacing.NORTH;

		state = state.withProperty(FACING, enumfacing).withProperty(BROKEN, BitFieldHelper.intToBool(BITFIELD.getValue(MFBlockConsts.NAME_BROKEN, meta)));

		return state;
	}

	@Override public int getMetaFromState(IBlockState state)
	{
		int meta = 0;

		meta = BITFIELD.setValue(MFBlockConsts.NAME_FACING, meta, state.getValue(FACING).getHorizontalIndex());
		meta = BITFIELD.setValue(MFBlockConsts.NAME_BROKEN, meta, BitFieldHelper.boolToInt(state.getValue(BROKEN)));

		return meta;
	}



	@Override public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return getStateFromMeta(meta).withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { BROKEN, FACING });
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

	@Override public boolean isOpaqueCube(IBlockState state) { return false; }
	@Override public boolean isFullCube(IBlockState state) { return false; }
	@Override public boolean isFullBlock(IBlockState state) { return false; }
	@Override public boolean getUseNeighborBrightness(IBlockState state) { return true; }


}
