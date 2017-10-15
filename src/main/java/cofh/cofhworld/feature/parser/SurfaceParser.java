package cofh.cofhworld.feature.parser;

import cofh.cofhworld.feature.distribution.FeatureBase;
import cofh.cofhworld.feature.distribution.FeatureBase.GenRestriction;
import cofh.cofhworld.feature.distribution.FeatureGenSurface;
import cofh.cofhworld.feature.distribution.FeatureGenTopBlock;
import cofh.cofhworld.init.FeatureParser;
import cofh.cofhworld.util.WeightedRandomBlock;
import cofh.cofhworld.util.numbers.INumberProvider;
import com.typesafe.config.Config;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SurfaceParser extends UniformParser {

	@Override
	protected List<WeightedRandomBlock> generateDefaultMaterial() {

		return Arrays.asList(new WeightedRandomBlock(Blocks.STONE, -1), new WeightedRandomBlock(Blocks.DIRT, -1), new WeightedRandomBlock(Blocks.GRASS, -1), new WeightedRandomBlock(Blocks.SAND, -1), new WeightedRandomBlock(Blocks.GRAVEL, -1), new WeightedRandomBlock(Blocks.SNOW, -1), new WeightedRandomBlock(Blocks.AIR, -1), new WeightedRandomBlock(Blocks.WATER, -1));
	}

	@Override
	protected FeatureBase getFeature(String featureName, Config genObject, WorldGenerator gen, INumberProvider numClusters, GenRestriction biomeRes, boolean retrogen, GenRestriction dimRes, Logger log) {

		// TODO: WorldGeneratorAdv that allows access to its material list
		List<WeightedRandomBlock> matList = defaultMaterial;
		if (genObject.hasPath("material")) {
			matList = new ArrayList<>();
			if (!FeatureParser.parseResList(genObject.root().get("material"), matList, false)) {
				log.warn("Invalid material list! Using default list.");
				matList = defaultMaterial;
			}
		}
		// TODO: clarity on follow-terrain field
		if (genObject.hasPath("follow-terrain") && genObject.getBoolean("follow-terrain")) {
			return new FeatureGenTopBlock(featureName, gen, matList, numClusters, biomeRes, retrogen, dimRes);
		} else {
			return new FeatureGenSurface(featureName, gen, matList, numClusters, biomeRes, retrogen, dimRes);
		}
	}

}
