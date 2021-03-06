package blocks;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class PrankBlock extends ModBlock 
{
	private Random randomizer;
	
	public PrankBlock() 
	{
		super(Material.ROCK, "prank_block");
		
		this.setCreativeTab(CreativeTabs.MATERIALS);
		randomizer = new Random();
	}
	
	@Override
    public boolean onBlockActivated(World world,
            BlockPos pos,
            IBlockState state,
            EntityPlayer player,
            EnumHand hand,
            EnumFacing side,
            float hitX,
            float hitY,
            float hitZ)
    {
    	int prank = randomizer.nextInt(7);
    	
    	if (prank == 0)
    		RandomlyTeleportUser(player);
    	else if (prank == 1)
    		BlowUpBlock(world, player);
    	else if (prank == 2)
    		ThrowUser(player);
    	else if (prank == 3)
    		DropAllPlayerActiveInventory(player);
    	else if (prank == 4)
			SetOnFire(player);
      	else if (prank == 5)
			MobPlayerWithCreepers(world, player);
	    else if (prank == 6)
			MobPlayerWithPigs(world, player);
    	
		return true;
    }
    
    private void RandomlyTeleportUser(EntityPlayer player)
    {
    	player.attemptTeleport(player.posX + 5, player.posY + 5, player.posZ + 5);
    }
    
    private void BlowUpBlock(World world, EntityPlayer player)
    {
    	Explosion explosion = world.createExplosion(player, player.posX, player.posY, player.posZ, 10.0f, true);
    	explosion.doExplosionA();
    	explosion.doExplosionB(true);
    }
    
    private void ThrowUser(EntityPlayer player)
    {
    	player.addVelocity(7, 5, 7);
    }
    
    private void DropAllPlayerActiveInventory(EntityPlayer player)
    {
    	player.inventory.dropAllItems();
    }
    
    private void SetOnFire(EntityPlayer player)
    {
    	player.setFire(15);
    }
    
    private void MobPlayerWithCreepers(World world, EntityPlayer player)
    {
    	int numberOfCreepersInMob = 100;
    	for (int i = 0; i < numberOfCreepersInMob; i++)
    	{
    		int randomX = -15 + randomizer.nextInt(30);
    		int randomZ = -15 + randomizer.nextInt(30);
    		
    		if (randomX == 0)
    			randomX += 5;
    		
    		if (randomZ == 0)
    			randomZ += 5;
    		
    		EntityCreeper creeper = new EntityCreeper(world);
    		creeper.setLocationAndAngles(player.posX + randomX, player.posY, player.posZ + randomZ, 1.0f, 1.0f);
    		world.spawnEntity(creeper);
    	}
    }
    
    private void MobPlayerWithPigs(World world, EntityPlayer player)
    {
    	int numberOfPigsInMob = 100;
    	for (int i = 0; i < numberOfPigsInMob; i++)
    	{
    		int randomX = -15 + randomizer.nextInt(30);
    		int randomZ = -15 + randomizer.nextInt(30);
    		
    		if (randomX == 0)
    			randomX += 5;
    		
    		if (randomZ == 0)
    			randomZ += 5;
    		
    		EntityPig pig = new EntityPig(world);
    		pig.setLocationAndAngles(player.posX + randomX, player.posY, player.posZ + randomZ, 1.0f, 1.0f);
    		world.spawnEntity(pig);
    	}
    }
}
