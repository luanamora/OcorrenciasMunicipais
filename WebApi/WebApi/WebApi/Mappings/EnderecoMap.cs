using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApi.Models;

namespace WebApi.Mappings
{
    public class EnderecoMap : IEntityTypeConfiguration<Endereco>
    {
        public void Configure(EntityTypeBuilder<Endereco> entity)
        {
            entity.HasKey(e => e.CdEndereco);

            entity.ToTable("endereco");

            entity.Property(e => e.CdEndereco).HasColumnName("cd_endereco");

            entity.Property(e => e.CdCidade).HasColumnName("cd_cidade");

            entity.Property(e => e.DsBairro)
                .IsRequired()
                .HasColumnName("ds_bairro")
                .HasColumnType("character varying(255)");

            entity.Property(e => e.DsComplemento)
                .IsRequired()
                .HasColumnName("ds_complemento")
                .HasColumnType("character varying(120)");

            entity.Property(e => e.DsLogradouro)
                .IsRequired()
                .HasColumnName("ds_logradouro")
                .HasColumnType("character varying(255)");

            entity.Property(e => e.DsNumero)
                .IsRequired()
                .HasColumnName("ds_numero")
                .HasColumnType("character varying(255)");

            entity.Property(e => e.DtAtualizacao)
                .HasColumnName("dt_atualizacao")
                .HasColumnType("date");

            entity.Property(e => e.DtCadastro)
                .HasColumnName("dt_cadastro")
                .HasColumnType("date");

            entity.Property(e => e.NrCep)
                .IsRequired()
                .HasColumnName("nr_cep")
                .HasColumnType("character varying(10)");

            entity.HasOne(d => d.CdCidadeNavigation)
                .WithMany(p => p.Endereco)
                .HasForeignKey(d => d.CdCidade)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("cidade_endereco_fk");
        }
    }
}
