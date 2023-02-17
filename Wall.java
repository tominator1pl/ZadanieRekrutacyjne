package zadanie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

interface Structure {
	// zwraca dowolny element o podanym kolorze
	Optional<Block> findBlockByColor(String color);

	// zwraca wszystkie elementy z danego materiału
	List<Block> findBlocksByMaterial(String material);

	//zwraca liczbę wszystkich elementów tworzących strukturę
	int count();
}

public class Wall implements Structure {
	private List<Block> blocks;

	@Override
	public Optional<Block> findBlockByColor(String color) {
		List<Block> allBlocks = getAllBlocks(blocks);
		for(int i = 0; i<allBlocks.size(); i++) {
			if(allBlocks.get(i).getColor().equals(color)) {
				return Optional.of(allBlocks.get(i));
			}
		}
		return Optional.empty();
	}

	@Override
	public List<Block> findBlocksByMaterial(String material) {
		List<Block> allBlocks = getAllBlocks(blocks);
		List<Block> blocksMaterial = new ArrayList<Block>();
		for(int i = 0; i<allBlocks.size(); i++) {
			if(allBlocks.get(i).getMaterial().equals(material)) {
				blocksMaterial.add(allBlocks.get(i));
			}
		}
		return blocksMaterial;
	}

	@Override
	public int count() {
		return getAllBlocks(blocks).size();
	}
	
	/**
	 * Recursively gets all Block type objects from List&ltBlock&gt if any of the object in List is a CompositeBlock
	 * @param List of Blocks to seperate
	 * @return Returns List&ltBlock&gt; containing only Block type objects
	 */
	private List<Block> getAllBlocks(List<Block> listBlocks){
		List<Block> allBlocks = new ArrayList<Block>();
		for(int i = 0; i<listBlocks.size(); i++) {
			if(listBlocks.get(i) instanceof CompositeBlock) {
				allBlocks.addAll(getAllBlocks(((CompositeBlock)listBlocks.get(i)).getBlocks()));
			}else {
				allBlocks.add(listBlocks.get(i));
			}
		}
		
		return allBlocks;
	}
}

interface Block {
	String getColor();
	String getMaterial();
}

interface CompositeBlock extends Block {
	List<Block> getBlocks();
}