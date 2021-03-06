package cofh.cofhworld.parser.generator;

import cofh.cofhworld.parser.IGeneratorParser;
import cofh.cofhworld.parser.variables.EntityData;
import cofh.cofhworld.util.random.WeightedBlock;
import cofh.cofhworld.util.random.WeightedNBTTag;
import com.typesafe.config.Config;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@Deprecated//TODO
public class GenParserDungeon implements IGeneratorParser {

	private static String[] FIELDS = new String[] { "block", "" };

	@Override
	public String[] getRequiredFields() {

		return FIELDS;
	}

	@Override
	@Nonnull
	public WorldGenerator parseGenerator(String name, Config genObject, Logger log, List<WeightedBlock> resList, List<WeightedBlock> matList) {

		ArrayList<WeightedNBTTag> mobList = new ArrayList<>();
		if (genObject.hasPath("spawnEntity")) {
			if (!EntityData.parseEntityList(genObject.getValue("spawnEntity"), mobList)) {
				log.warn("Entry specifies invalid entity list for 'dungeon' generator! Using 'Pig'!");
				mobList.clear();
				NBTTagCompound tag = new NBTTagCompound();
				tag.setString("EntityId", "Pig");
				mobList.add(new WeightedNBTTag(100, tag));
			}
		} else {
			log.warn("Entry specifies invalid entity list for 'dungeon' generator! Using 'Pig'!");
			NBTTagCompound tag = new NBTTagCompound();
			tag.setString("EntityId", "Pig");
			mobList.add(new WeightedNBTTag(100, tag));
		}
		/*WorldGenDungeon r = new WorldGenDungeon(resList, matList, mobList);
		if (genObject.has("spawnerFloor")) {
			resList = new ArrayList<WeightedRandomBlock>();
			if (FeatureParser.parseBlockList(genObject.get("spawnerFloor"), resList, true)) {
				r.floor = resList;
			} else {
				log.warn("Entry specifies invalid block list for 'spawnerFloor'! Using walls.");
			}
		}
		{
			if (genObject.has("lootTable")) {
				ArrayList<DungeonMob> lootList = new ArrayList<DungeonMob>();
				if (FeatureParser.parseWeightedStringList(genObject.get("lootTable"), lootList)) {
					r.lootTables = lootList;
				} else {
					log.warn("Entry specifies invalid string list for 'lootTable'! Using default.");
				}
			}
			if (genObject.has("maxChests")) {
				r.maxChests = genObject.get("maxChests").getAsInt();
			}
			if (genObject.has("chestAttempts")) {
				r.maxChestTries = MathHelper.clamp(genObject.get("chestAttempts").getAsInt(), 1, 5);
			}

			if (genObject.has("minHoles")) {
				r.minHoles = genObject.get("minHoles").getAsInt();
			}
			if (genObject.has("maxHoles")) {
				r.maxHoles = genObject.get("maxHoles").getAsInt();
			}

			if (genObject.has("minHeight")) {
				r.minHeight = genObject.get("minHeight").getAsInt();
			}
			if (genObject.has("maxHeight")) {
				r.maxHeight = genObject.get("maxHeight").getAsInt();
			}

			if (genObject.has("minWidthX")) {
				r.minWidthX = genObject.get("minWidthX").getAsInt();
			}
			if (genObject.has("maxWidthX")) {
				r.maxWidthX = genObject.get("maxWidthX").getAsInt();
			}
			if (genObject.has("minWidthZ")) {
				r.minWidthZ = genObject.get("minWidthZ").getAsInt();
			}
			if (genObject.has("maxWidthZ")) {
				r.maxWidthZ = genObject.get("maxWidthZ").getAsInt();
			}
		}*/
		throw new UnsupportedOperationException();
	}

}
