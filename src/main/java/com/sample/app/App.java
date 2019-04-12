package com.sample.app;

import com.sample.app.generators.BlockChainGenerator;
import com.sample.app.model.Wallet;

public class App {
	public static void main(String args[]) throws Exception {

		// Start new block chain
		BlockChainGenerator bukaChainGenerator = new BlockChainGenerator();
		bukaChainGenerator.startNewBlockChain();

		// Create wallets
		Wallet bobWallet = bukaChainGenerator.newWallet();
		Wallet aliceWallet = bukaChainGenerator.newWallet();
		Wallet johnWallet = bukaChainGenerator.newWallet();

		// Bob sent 100 buka coins alice
		bobWallet.sendMoney(aliceWallet.getPublicKey(), 100);

		// John sent 50 buka coins to alice
		johnWallet.sendMoney(aliceWallet.getPublicKey(), 50);

		// Alice sent 20.76 buka coins to Bob
		aliceWallet.sendMoney(bobWallet.getPublicKey(), 20.76);

		// Print block chain, if it is valid
		if (bukaChainGenerator.isChainvalid()) {
			bukaChainGenerator.printBlockChain();
		}

	}
}
