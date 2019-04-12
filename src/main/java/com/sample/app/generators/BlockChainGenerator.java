package com.sample.app.generators;

import java.security.NoSuchAlgorithmException;

import com.sample.app.constants.Constants;
import com.sample.app.model.Block;
import com.sample.app.model.Transaction;
import com.sample.app.model.Wallet;
import com.sample.app.util.HashUtil;
import com.sample.app.util.JSONUtil;
import com.sample.app.util.SignatureUtil;

public class BlockChainGenerator {

	private Block<Transaction> rootBlock = null;
	private Block<Transaction> lastBlock = null;

	public void startNewBlockChain() {
		rootBlock = new Block<Transaction>();

		rootBlock.setPreviousBlockHashCode("");

		// HashUtil.update(rootBlock);

		lastBlock = rootBlock;

	}

	public void createNewBlock(Transaction transactionalData) {
		Block<Transaction> block = new Block<>();
		block.setTransactionalData(transactionalData);
		block.setPreviousBlockHashCode(lastBlock.getHashCode());
		lastBlock.setNextBlock(block);
		lastBlock = block;

		HashUtil.setHashCode(block);
	}

	public void printBlockChain() {
		System.out.println(JSONUtil.getOnlyExposedJson(rootBlock));
	}

	public boolean isChainvalid() {
		if (rootBlock == null)
			return true;

		Block<Transaction> nextBlock = rootBlock.getNextBlock();

		if (nextBlock == null)
			return true;

		Block<Transaction> currentBlock = nextBlock;
		nextBlock = currentBlock.getNextBlock();

		while (nextBlock != null) {
			if (!nextBlock.getPreviousBlockHashCode().equals(currentBlock.getHashCode())) {
				return false;
			}

			currentBlock = nextBlock;
			nextBlock = nextBlock.getNextBlock();
		}

		return true;

	}


	public boolean submitTransaction(Transaction transaction, String algorithmToSign) throws Exception {
		boolean isvalid = SignatureUtil.verifySignature(transaction, transaction.getSourceAddress(), algorithmToSign);

		if (!isvalid) {
			return false;
		}

		this.createNewBlock(transaction);
		return true;

	}

	public Wallet newWallet() throws NoSuchAlgorithmException {
		Wallet wallet = WalletGenerator.newWallet(Constants.ALGORITHM_TO_GENERATE_KEY_PAIR);
		wallet.setBlockChainGenerator(this);
		return wallet;
	}
}

